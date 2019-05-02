package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.javaparser.ast.CompilationUnit;

import detector.Detector;
import detector.Report;
import userbase.UserBase;

/**
 * Decompress the file uploaded by user
 */
@WebServlet("/ZipDecompServlet")
public class ZipDecompServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime=System.currentTimeMillis();	
		String sessionID = request.getSession().getId();
		String filePathIn = UserBase.getUser(sessionID).getZipAddress();
		System.out.println(filePathIn);
		String unzippedPath = filePathIn.substring(0, filePathIn.lastIndexOf("."));
		UserBase.getUser(sessionID).setUnzippedAddress(unzippedPath);
		String filePathOut = unzippedPath + "/";
		File pathFile = new File(filePathOut);
		
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		
		ZipFile zip = new ZipFile(filePathIn, Charset.forName("GBK"));	// deal with messy code
		
		for(Enumeration entries = zip.entries(); entries.hasMoreElements();)
		{
			ZipEntry entry = (ZipEntry)entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (filePathOut+zipEntryName).replaceAll("\\*", "/");;
			
			// check if the file exists, create a new one if it deoesn't
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			
			if(!file.exists()){
				file.mkdirs();
			}
			
			// if a directory, keep it
			if(new File(outPath).isDirectory()){
				continue;
			}
			
			// output
			//System.out.println(outPath);
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			
			while((len=in.read(buf1))>0){
				out.write(buf1,0,len);
			}
			
			in.close();
			out.close();
		}
		
		
		zip.close();
		System.out.println("******************Finish********************");
        long endTime=System.currentTimeMillis();  
        System.out.println("Time consume£º "+(endTime-startTime)+" ms"); 
        
        request.getRequestDispatcher("result.html").forward(request, response);
        
        // parse the file, and store the compilationUnit into the user with sessionID = key in the hash map
        UserBase.getUser(sessionID).setCompilationUnit(UserBase.getUser(sessionID).getUnzippedAddress());
        
        // Detect smells in the project
        Detector detector = new Detector();
        detector.detect(UserBase.getUser(sessionID).getCompilationUnit());
        
        // store the detector into UserInfo
        UserBase.getUser(sessionID).addSmells(detector);
        
        Report report = new Report(detector);
        report.analyzeProject();
        UserBase.getUser(sessionID).setReport(report);
        
        // convert Cu to String to be displayed as source code on HTML
        convertCuToString(sessionID);
	}
	
	public static void convertCuToString(String sessionID) {
		
		List<CompilationUnit> compilationUnit = UserBase.getUser(sessionID).getCompilationUnit();
		for(CompilationUnit cu : compilationUnit) {
			String class_name = cu.getType(0).getNameAsString();
			//System.out.println(class_name);
			UserBase.getUser(sessionID).setClassName(class_name);
			UserBase.getUser(sessionID).setSourceCode(class_name, cu.toString());
		}	
	}
}
