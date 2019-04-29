package servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowSmellServlet
 */
@WebServlet("/ShowSmellServlet")
public class ShowSmellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getSession().getId();
		String fileKey = request.getParameter("id");
		Object smells = request.getAttribute("name");
		//smells.
		System.out.println(fileKey);
		System.out.println(smells);
		
	}

}
