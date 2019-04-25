package servlets;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fileaddress_base.FileMap;
import parser.CompilationUnitMap;

/**
 * Servlet implementation class ZipDecompServlet
 */
@WebServlet("/ZipDecompServlet")
public class ZipDecompServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int buffer = 2048;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long startTime=System.currentTimeMillis();	
		String sessionID = request.getSession().getId();
		String filePathIn = FileMap.getFile(sessionID);
		System.out.println(filePathIn);
		String filePathOut = filePathIn.substring(0, filePathIn.lastIndexOf(".")) + "/";
		
		File pathFile = new File(filePathOut);
		if(!pathFile.exists()){
		pathFile.mkdirs();
		}
		// deal with messy code
		ZipFile zip = new ZipFile(filePathIn, Charset.forName("GBK"));
		for(Enumeration entries = zip.entries(); entries.hasMoreElements();)
		{
		ZipEntry entry = (ZipEntry)entries.nextElement();
		String zipEntryName = entry.getName();
		InputStream in = zip.getInputStream(entry);
		String outPath = (filePathOut+zipEntryName).replaceAll("\\*", "/");;
		// check if the file exists, create a new one if it deoesn't
		File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
		if(!file.exists())
		{
		file.mkdirs();
		}
		// if a directory, keep it
		if(new File(outPath).isDirectory())
		{
		continue;
		}
		// output
		System.out.println(outPath);
		OutputStream out = new FileOutputStream(outPath);
		byte[] buf1 = new byte[1024];
		int len;
		while((len=in.read(buf1))>0)
		{
		out.write(buf1,0,len);
		}
		in.close();
		out.close();
		}
		System.out.println("******************Finish********************");
        long endTime=System.currentTimeMillis();  
        System.out.println("Time consume£º "+(endTime-startTime)+" ms"); 
        
        // parser the file, and store the compilationUnit into a hashmap with sessionID as the key
        CompilationUnitMap.addCompilationUnit(filePathOut.substring(0, filePathOut.lastIndexOf("/")), sessionID);
        
        // put the user's compilation unit into the request and jump to the test.jsp
        request.setAttribute("compilationUnit", CompilationUnitMap.getCompilationUnit(sessionID));
        request.getRequestDispatcher("./pages/test.jsp").forward(request, response);
//        System.out.println("Forward to test.jsp");
	} 
}
