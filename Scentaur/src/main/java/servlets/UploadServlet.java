package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import filebase.FileMap;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// root directory
		String savePath = request.getSession().getServletContext().getRealPath("/");
		File file = new File(savePath);
		// check if the root exists
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath+"file doesn't exist, a new one has been created");
			// creat the root file
			file.mkdir();
		}
		// alert
		String message = "";
		try{
			//use Apache FileUpload Module to deal with file uploading£º
			//1¡¢instantiate a DiskFileItemFactory
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2¡¢instantiate a ServletFileUpload resolver
			ServletFileUpload upload = new ServletFileUpload(factory);
			// deal with messy code
			upload.setHeaderEncoding("UTF-8"); 
			//3¡¢check if the data belongs to the form
			if(!ServletFileUpload.isMultipartContent(request)){
				
				System.out.println("no file uploaded");

			}
           //4¡¢use ServletFileUpload resolver to analyze uploaded items£¬return a List<FileItem>£¬every FileItem is a Form input
           List<FileItem> list = upload.parseRequest(request);
           for(FileItem item : list){
               // if items in the list are data
               if(item.isFormField()){
                   String name = item.getFieldName();
                   // deal with messy code
		          String value = item.getString("UTF-8");
		          //value = new String(value.getBytes("iso8859-1"),"UTF-8");
		          System.out.println(name + "=" + value);
		      }else{// if items in the list are files
                   // obtain the file name
                   String filename = item.getName();
                   System.out.println(filename);
                   if(filename==null || filename.trim().equals("")){
                       continue;
                   }
		          
		          // obtain the file name without address
		          filename = filename.substring(filename.lastIndexOf("\\")+1);
		          // rename the file which begins with the substring of the request from "@" to the end
		          // for instance C:\X\XX\@xxxxx-filename
		          String absoluteAdd = new String(savePath + "\\" + request.toString().substring(request.toString().lastIndexOf("@")) +"-"+ filename);
		          // obtain the upload stream of the file
		          InputStream in = item.getInputStream();
                   // instantiate an output stream
                   FileOutputStream out = new FileOutputStream(absoluteAdd);
                   // a buffer
                   byte buffer[] = new byte[1024];
                   // a mark for checking if the input stream is empty
		          int len = 0;
		          // read the input stream into the buffer£¬(len=in.read(buffer))>0 means 'in' is not empty
		          while((len=in.read(buffer))>0){
		              // use FileOutputStream to write the data in buffer into (savePath + "\\" + filename)
		              out.write(buffer, 0, len);
		          }
                   // close input stream
                   in.close();
                   // close output stream
                   out.close();
		          // eliminate temporary file created during upload
		          item.delete();
		          message = "Success to upload file£¡";
		          System.out.println(message);
		          // obtain the session ID
		          HttpSession session = request.getSession();
		          String userID = session.getId();
		          // store the file into the hashmap
		          // key: session ID	value: the absolute address of the file
		          FileMap.addFile(userID, absoluteAdd);
		          System.out.println("User id: "+userID);
		          // send the request to ZipDecompservlet
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
