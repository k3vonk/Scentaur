package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userbase.UserBase;

/**
 * response the request when user chooses java files
 * return the java file
 * 
 */
@WebServlet("/SourceCodeServlet")
public class SourceCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getSession().getId();
		String index = request.getParameter("id");
		int key = Integer.parseInt(index);
		// since index 0 is overview, make overview and first option show the same java file.
		if(key-1<0) {
			key = 1;
		}
		String className = UserBase.getUser(sessionID).getClassNames().get(key-1);
		String sourceCode = UserBase.getUser(sessionID).getSourceCode(className);
		
		PrintWriter out = response.getWriter();
		out.print(sourceCode);
	}

}
