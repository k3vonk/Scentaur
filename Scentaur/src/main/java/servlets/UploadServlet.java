package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import userbase.UserBase;
import userbase.UserInfo;

/**
 * Receive the file uploaded by user
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// Root directory
		String savePath = request.getSession().getServletContext().getRealPath("/")+"UserFiles";
		File file = new File(savePath);
		
		// Check if the root exists...
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath+"file doesn't exist, a new one has been created");
			file.mkdir();	// Create the root file
		}
		
		// Alert
		String message = "";
		try{
			//use Apache FileUpload Module to deal with file uploading£º
			DiskFileItemFactory factory = new DiskFileItemFactory();	//1.instantiate a DiskFileItemFactory
			ServletFileUpload upload = new ServletFileUpload(factory);	//2.instantiate a ServletFileUpload resolver
			upload.setHeaderEncoding("UTF-8"); 							// deal with messy code
			
			//3.check if the data belongs to the form...
			if(!ServletFileUpload.isMultipartContent(request)){ System.out.println("no file uploaded");}
			
           //4.use ServletFileUpload resolver to analyze uploaded items£¬return a List<FileItem>£¬every FileItem is a Form input
           List<FileItem> list = upload.parseRequest(request);
           
           for(FileItem item : list){
          
               if(item.isFormField()){ // if items in the list are data...
                   String name = item.getFieldName();
                   String value = item.getString("UTF-8"); // deal with messy code
                   
		          System.out.println(name + "=" + value); //value = new String(value.getBytes("iso8859-1"),"UTF-8");
		      }else{// else items in the list are files
                  
                   String filename = item.getName(); // obtain the file name
                   System.out.println(filename);
                   if(filename==null || filename.trim().equals("")){
                       continue;
                   }
		          
		          filename = filename.substring(filename.lastIndexOf("\\")+1); // obtain the file name without address
		          // rename the file which begins with the substring of the request after "@" to the end
		          // for instance C:\X\XX\Xxxxxxfilename with the first lowercase "x" is the first char after @, the "X" before "x" is just the letter "X"
		          String absoluteAdd = new String(savePath + "\\" + "X" + request.toString().substring(request.toString().lastIndexOf("@")+1) + filename);
		          InputStream in = item.getInputStream(); 						// obtain the upload stream of the file
                  FileOutputStream out = new FileOutputStream(absoluteAdd); 	// instantiate an output stream
                   
                  byte buffer[] = new byte[1024];	
                  int len = 0;	// a mark for checking if the input stream is empty
		          
		          // read the input stream into the buffer£¬(len=in.read(buffer))>0 means 'in' is not empty
		          while((len=in.read(buffer))>0){
		              
		              out.write(buffer, 0, len);	// use FileOutputStream to write the data in buffer into (savePath + "\\" + filename)
		          }
                   
                  in.close();	// close input stream
                  out.close(); // close output stream
		          item.delete();	// eliminate temporary file created during upload
		          message = "Success to upload file£¡";
		          
		          System.out.println(message);
		          
		          UserInfo user = new UserInfo(); // instantiate a user, store the zip address into user
		          user.setZipAddress(absoluteAdd);
		          HttpSession session = request.getSession(); // obtain the session ID
		          String sessionID = session.getId();
		          
		          // Store the user into the HashMap
		          // Key: session ID	Value: the user
		          UserBase.addUser(sessionID, user);
		          System.out.println("User id: "+sessionID);
		          
		          // Send the request to ZipDecompservlet
		          request.getRequestDispatcher("ZipDecompServlet").forward(request, response);
		      }
		  }
       }catch (Exception e) {
		  message= "Fail to upload file£¡";
		  System.out.println(message);
          e.printStackTrace();
           
       }
	}
}
