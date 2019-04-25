package parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

/*
 * the .java files in the file the user uploaded is parsed into a list of compilation unit
 * 
 * every list of copilation unit is stored into a hashmap called compilationUnit
 * 
 * the key is the session ID of that user, value is the list of CU which belongs to the user
 * 
 */
public class CompilationUnitMap {
	
	private static Map<String, List<CompilationUnit>> compilationUnit =
			new HashMap<String, List<CompilationUnit>>();

	public static void addCompilationUnit(String path, String sessionID) throws IOException{
		Parser parse = new Parser(path);
		List<CompilationUnit> all = parse.getAllCu();
		compilationUnit.put(sessionID, all);
	}
	
	public static List<CompilationUnit> getCompilationUnit(String sessionID) {
		return compilationUnit.get(sessionID);
	}
	
	public static void removeCompilationUnit(String sessionID) {
		compilationUnit.remove(sessionID);
	}
}
