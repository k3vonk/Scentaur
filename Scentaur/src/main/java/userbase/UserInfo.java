package userbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import detector.Detector;
import detector.Report;
import parser.Parser;

/**
 * A class that holds user info, thus other users can't obtain this info
 *
 */
public class UserInfo {
	private String zipAddress; 		// Disk:/xxxxx/xxxx/xxxx/abc.zip
	private String UnzippedAddress; // Disk:/xxxxx/xxxx/xxxx/abc
	private List<CompilationUnit> compilationUnit;
	private List<String> classNames = new ArrayList<String>(); // name of the java file
	private Map<String, String> sourceCode = new HashMap<String, String>(); // <name_java, code_java>
	private Detector smells;
	private Report report;

	public String getZipAddress() {
		return this.zipAddress;
	}
	
	public void setZipAddress(String zipAddress) {
		this.zipAddress = zipAddress;
	}
	
	public String getUnzippedAddress() {
		return this.UnzippedAddress;
	}
	
	public void setUnzippedAddress(String unzippedAddress) {
		this.UnzippedAddress = unzippedAddress;
	}
	
	public List<CompilationUnit> getCompilationUnit() {
		return this.compilationUnit;
	}
	
	public void setCompilationUnit(String path) throws IOException{
		Parser parse = new Parser(path);
		this.compilationUnit = parse.getAllCu();
	}
	
	public void setClassName(String className) {
		this.classNames.add(className);
	}
	
	public List<String> getClassNames() {
		return this.classNames;
	}
	
	public String getSourceCode(String class_name) {
		return this.sourceCode.get(class_name);		
	}
	
	public void setSourceCode(String class_name, String source_code) {
		this.sourceCode.put(class_name, source_code);
	}
	
	public Map<String, String> getSourceCodeMap(){
		return this.sourceCode;
	}
	
	public void addSmells(Detector smell) {
		this.smells = smell;
	}
	
	public String getSmellsByFileNameAndType(int index, String fileName, String requestSmell) {
		if(requestSmell.equals("abuser")) {		
			return this.smells.getAbuserSmell(index, fileName);
		}
		else if(requestSmell.equals("bloater")) {
			return this.smells.getBloaterSmell(index, fileName);
		}
		else if(requestSmell.equals("coupler")) {
			return this.smells.getCouplerSmell(index, fileName);
		}
		else {
			return this.smells.getDispensableSmell(index, fileName);
		}
	}
	
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	public String getReportFileAddr() {
		return this.UnzippedAddress+"/SmellReport.txt";
	}

}
