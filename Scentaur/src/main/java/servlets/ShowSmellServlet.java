package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import userbase.UserBase;

/**
 * Servlet implementation class ShowSmellServlet
 */
@WebServlet("/ShowSmellServlet")
public class ShowSmellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionID = request.getSession().getId();
		String fileKey = request.getParameter("id");
		String smellsInUrl = request.getParameter("formData");
		int fileKeyInt = Integer.parseInt(fileKey);
		
		List<String> smells = new ArrayList<String>();
		PrintWriter out = response.getWriter();
		Object[] res = new String[3];
		
		// smell request is formatted in "bloaters=Bloaters&dispensables=Dispensables"
		// split it into a list containing only the values [Bloaters, Dispensables,....]
		String[] arrSplit = smellsInUrl.split("&");
		for(String strSplit : arrSplit) {
			String [] keyAndValue = strSplit.split("=");
			if(keyAndValue.length>1) {
				smells.add(keyAndValue[1]);
			}		
		}

		
		if(fileKeyInt>0) {
			String fileName = UserBase.getUser(sessionID).getClassNames().get(fileKeyInt-1);
			String smellsReturn = "";
			res[0] = fileName;
			res[2] = UserBase.getUser(sessionID).getSourceCode(fileName);
			
			if(smells.size()>0) {
				for(String s: smells) {
					smellsReturn += s.toUpperCase()+":\n"+UserBase.getUser(sessionID).getSmellsByFileNameAndType(fileKeyInt-1, fileName, s)+"\n\n";
				}
				res[1] = smellsReturn;				
			}
			else {
				res[1] = "No smell selected!";
			}
			
			String json = JSONArray.fromObject(res).toString();		
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/json; charset=utf-8");
			
			out.print(json);
		}
		else {
			String fileName = UserBase.getUser(sessionID).getClassNames().get(0);
			res[0] = "Overview";
			res[1] = UserBase.getUser(sessionID).getReport().showSmellStatistics();
			res[2] = UserBase.getUser(sessionID).getSourceCode(fileName);
			
			String json = JSONArray.fromObject(res).toString();		
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("text/json; charset=utf-8");
			
			out.print(json);
		}
	}

}
