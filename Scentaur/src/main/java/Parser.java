import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

public class Parser{

    private File file;
    private CompilationUnit cu;
    private MethodVisitor m;

    @SuppressWarnings("unchecked")
	public Parser(String filePath){
        this.file = new File(filePath);
        
        try {
			cu = JavaParser.parse(file);
			m = new MethodVisitor();
			m.visit(cu, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

    }
    
	/**
	 * @return the name of the directory containing the file
	 */
	public String getParentDirectory() {
		return file.getParentFile().getName();
	}
	
	/**
	 * @return number of lines in a file
	 */
	public long fileLength(){
		try {
			return Files.lines(file.toPath()).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	/**
	 * @return the amount of comments in a file
	 */
	public int commentAmount() {
		return cu.getComments().size();
	}
	
	public String fileName() {
		return file.getName();
	}
	
	public HashMap<String, Integer> getMethod(){
		return m.getMethod();
	}
	
	public String toString() {
		String s = String.format("============= %s =============\n", fileName());
		s += m.toString();
		s += "File Length: " + fileLength() + "\n";
		s += "Parent Directory: " + getParentDirectory() + "\n";
		return s;
	}
	    
    private int countLines(MethodDeclaration method) {
        return method.getBody().toString().split("\r\n|\r|\n").length;
    }

    @SuppressWarnings("rawtypes")
	private class MethodVisitor extends VoidVisitorAdapter {
 
    	private HashMap<String, Integer> method = new HashMap<>();
    	
        public void visit(MethodDeclaration n, Object arg) {
            method.put(n.getNameAsString(), countLines(n));
        }
        
        public HashMap<String,Integer> getMethod(){
        	return method;
        }
        
        public String toString() {
        	String str = "";
        	
        	for(String s: method.keySet()) {
        		str += String.format("%-18s | Number of lines[%d]\n", s,method.get(s));
        	}
        	
        	return str;
        }
    }




}