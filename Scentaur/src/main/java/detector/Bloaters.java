package detector;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import smell.bloater.LargeClass;
import smell.bloater.LongMethod;
import smell.bloater.LongParameterList;
import smell.bloater.PrimitiveObsession;
import userbase.UserBase;

public class Bloaters {
	String sessionID;
	public Bloaters(String sessionID) {
		this.sessionID = sessionID;
	}
	public void detect(List<CompilationUnit> all) throws IOException {
		
		
		for(CompilationUnit c: all){
			String smells = "";
			String className = c.getType(0).getNameAsString();
			Map<String, String> bloaters = new HashMap<String, String>();
			
			String longP = longParaList(c);
			String longM = longMethod(c);
			String largeC = largeClass(c);
			String primOb = PrimitiveObsession(c);
			
			smells += longP+longM+largeC+primOb;
			bloaters.put("Bloaters", smells);
			UserBase.getUser(sessionID).addSmells(className, bloaters);
		}


	}
	
	public String longParaList(CompilationUnit c) {
		LongParameterList longParameterList = new LongParameterList();
		// Test Long Param List
			c.accept(longParameterList, null);			
		
		/*System.out.println(longParameterList.getClass());
		for(Node node: longParameterList.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}*/
		if(longParameterList.getIssue().size()>0) {
			UserBase.getUser(sessionID).increaseLongParameterList();
			String lineNumber = longParameterList.getIssue().get(0).getBegin().get().line+"";
			String smellCode = longParameterList.getIssue().get(0).toString();			
			return "Long Parameter List at line: "+lineNumber+"\n"+smellCode+"\n\n";
		}
		else {
			return "";
		}
		
	}
	
	public String longMethod(CompilationUnit c) {
		LongMethod longMethod = new LongMethod();
		c.accept(longMethod, null);			
		
		/*System.out.println(longMethod.getClass());
		for(Node node: longMethod.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}*/
		if(longMethod.getIssue().size()>0)	{
			UserBase.getUser(sessionID).increaseLongMethod();
			String lineNumber = longMethod.getIssue().get(0).getBegin().get().line+"";
			String smellCode = longMethod.getIssue().get(0).toString();
			
			return "Long Method at line: "+lineNumber+"\n"+smellCode+"\n\n";
		}
		else {
			return "";
		}
		
	}
	
	public String largeClass(CompilationUnit c) {
		LargeClass largeClass = new LargeClass();

		c.accept(largeClass, null);			

		/*System.out.println(largeClass.getClass());
		for(Node node : largeClass.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}*/
		if(largeClass.getIssue().size()>0) {
			UserBase.getUser(sessionID).increaseLargeClass();
			String lineNumber = largeClass.getIssue().get(0).getBegin().get().line+"";
			String smellCode = largeClass.getIssue().get(0).toString();
			
			return "Large Class at line: "+lineNumber+"\n"+smellCode+"\n\n";
		}
		else {
			return "";
		}
		
	}
	
	public String PrimitiveObsession(CompilationUnit c) {
		PrimitiveObsession primObsession = new PrimitiveObsession();
		
		c.accept(primObsession, null);			

		/*System.out.println(primObsession.getClass());
		for(Node node : primObsession.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}*/
		
		if(primObsession.getIssue().size()>0) {
			UserBase.getUser(sessionID).increasePrimitiveObsession();
			String lineNumber = primObsession.getIssue().get(0).getBegin().get().line+"";
			String smellCode = primObsession.getIssue().get(0).toString();
			
			return "Primitive Obsession at line: "+lineNumber+"\n"+smellCode+"\n\n";
		}
		else {
			return "";
		}
		
	}
}
