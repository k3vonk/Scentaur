package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.javaparser.ast.CompilationUnit;

import net.sf.json.JSONArray;
import userbase.UserBase;

/**
 * Response to the request when result.html is loading
 * The first java file in the compilation unit is returned as well as an array of names of all the java files
 */
@WebServlet("/PageOnloadServlet")
public class PageOnloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getSession().getId();

		String key = UserBase.getUser(sessionID).getClassNames().get(0); // the key for first java file, and return that file
		String overview = UserBase.getUser(sessionID).getSourceCodeMap().get(key);
		
		// form the response as res[0] = the source to display, res[1] = an array of all the java file names
		List<String> classNames = UserBase.getUser(sessionID).getClassNames();
		Object [] res = new Object[5];
		res[0] = overview; 				// source code of the first java file
		res[1] = classNames.toArray(); 	// names of all the java files
		res[2] = key; 					// name of the first java file
		res[3] = "Overview";
		res[4] = UserBase.getUser(sessionID).getReport().showSmellStatistics();
		
		// Convert to JSON string
		String json = JSONArray.fromObject(res).toString();		
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(json);
	}
	
	// Convert compilation unit to string
	public static List<String> convertCuToString(List<CompilationUnit> compilationUnit){
		List<String> sourceCode = new ArrayList<String>();
		for(CompilationUnit cu : compilationUnit) {
			sourceCode.add(cu.toString());
		}		
		return sourceCode;
	}
	

}
