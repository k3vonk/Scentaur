package userbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import parser.Parser;

public class UserInfo {
	private String zipAddress; 		// Disk:/xxxxx/xxxx/xxxx/abc.zip
	private String UnzippedAddress; // Disk:/xxxxx/xxxx/xxxx/abc
	private List<CompilationUnit> compilationUnit;
	private List<String> classNames = new ArrayList<String>(); // name of the java file
	private Map<String, String> sourceCode = new HashMap<String, String>(); // <name_java, code_java>
	// key = name of java file, value = hash map of smells
	private Map<String, Map<String, String>> smells = new HashMap<String, Map<String, String>>();
	
	private int longParameterList = 0;
	private int longMethod = 0;
	private int largeClass = 0;
	private int primitiveObsession = 0;
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
	
	public void addSmells(String fileName, Map<String, String> smell) {
		this.smells.put(fileName, smell);
	}
	
	public String getSmellsByType(String fileName, String smellType) {
		return this.smells.get(fileName).get(smellType);
	}
	
	public Map<String, String> getSmellsMapByFileName(String fileName){
		return this.smells.get(fileName);
	}
	
	public Map<String, Map<String, String>> getSmellsMap(){
		return this.smells;
	}
	
	public int getLongParameterList() {
		return longParameterList;
	}
	
	public void increaseLongParameterList() {
		this.longParameterList += 1;
	}
	
	public int getLongMethod() {
		return longMethod;
	}
	
	public void increaseLongMethod() {
		this.longMethod += 1;
	}
	
	public int getLargeClass() {
		return largeClass;
	}
	
	public void increaseLargeClass() {
		this.largeClass += 1;
	}
	
	public int getPrimitiveObsession() {
		return primitiveObsession;
	}
	
	public void increasePrimitiveObsession() {
		this.primitiveObsession = 1;
	}

}
