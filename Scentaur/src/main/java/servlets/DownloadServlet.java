package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import userbase.UserBase;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getSession().getId();
		UserBase.getUser(sessionID).getReport().generateTextFileReport(UserBase.getUser(sessionID).getReportFileAddr());
		String filePath = UserBase.getUser(sessionID).getReportFileAddr();
		
		response.setHeader("Content-Disposition", "attachment;filename=SmellReport.txt");
		
		String mimeType = this.getServletContext().getMimeType("SmellReport.txt");
		response.setContentType(mimeType);
		
		ServletOutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(filePath);
		byte[] buffer = new byte[2048];
		int len = 0;
		while((len=in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		in.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
