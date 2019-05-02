package detector;

import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import smell.Smell;
import smell.Smell.Abusers;
import smell.Smell.Bloaters;
import smell.Smell.Couplers;
import smell.Smell.Dispensables;
import smell.abuser.Abuser;
import smell.bloater.Bloater;
import smell.coupler.Coupler;
import smell.dispensable.Dispensable;

/**
 * A class that reports on the different types of smells to users
 *
 */
public class Report {
	
	private Abuser abuser;
	private Bloater bloater;
	private Coupler coupler;
	private Dispensable dispensable;
	private Statistics smellStatistics;
	
	public Report() {
		this.abuser = new Abuser();
		this.bloater = new Bloater();
		this.coupler = new Coupler();
		this.dispensable = new Dispensable();
	}
	
	/**
	 * Analyzes the Project for abusers, bloaters, couplers and dispensables
	 * @param cu - the project in compilation unit 
	 */
	public void analyzeProject(List<CompilationUnit> cu) {
		abuser.detect(cu);
		bloater.detect(cu);
		coupler.detect(cu);
		dispensable.detect(cu);
		smellStatistics = new Statistics(abuser, bloater, coupler, dispensable);
	}	
	
	/**
	 * This class is for Jackie
	 */
	public void generateOverviewReport() {
		//===================Type of Smell Categories
		List<Map<String, Map<Abusers, Smell>>> abuses = abuser.getAbusers();
		List<Map<String, Map<Bloaters, Smell>>> bloats = bloater.getBloaters();
		List<Map<String, Map<Couplers, Smell>>> couples = coupler.getCouplers();
		List<Map<String, Map<Dispensables, Smell>>> dispenses = dispensable.getDispensables();
		
		//UserBase.getUser(sessionID).addSmells(ClassName, bloaters);
		//===================ABUSERS
		for(Map<String,Map<Abusers,Smell>> map: abuses) {
			for(String fileName: map.keySet()) {	//FileName 
				for(Abusers abuse: map.get(fileName).keySet()) {	//Enum 
						if(!map.get(fileName).get(abuse).getIssue().isEmpty()) { //Dont print things that are empty
							System.out.println(fileName + " " + map.get(fileName).get(abuse).toString(abuse.toString()));
						}
				}
			}
		}
		
		//===================BLOATERS
		for(Map<String,Map<Bloaters,Smell>> map: bloats) {
			for(String fileName: map.keySet()) {	//FileName 
				for(Bloaters bloat: map.get(fileName).keySet()) {	//Enum 
						if(!map.get(fileName).get(bloat).getIssue().isEmpty()) { //Dont print things that are empty
							System.out.println(fileName + " " + map.get(fileName).get(bloat).toString(bloat.toString()));
						}
				}
			}
		}
		
		//===================COUPLERS
		for(Map<String,Map<Couplers,Smell>> map: couples) {
			for(String fileName: map.keySet()) {	//FileName 
				for(Couplers couple: map.get(fileName).keySet()) {	//Enum 
						if(!map.get(fileName).get(couple).getIssue().isEmpty()) { //Dont print things that are empty
							System.out.println(fileName + " " + map.get(fileName).get(couple).toString(couple.toString()));
						}
				}
			}
		}
		
		//===================DISPENSABLES
		for(Map<String,Map<Dispensables,Smell>> map: dispenses) {
			for(String fileName: map.keySet()) {	//FileName 
				for(Dispensables dispense: map.get(fileName).keySet()) {	//Enum 
						if(!map.get(fileName).get(dispense).getIssue().isEmpty()) { //Dont print things that are empty
							System.out.println(fileName + " " + map.get(fileName).get(dispense).toString(dispense.toString()));
						}
				}
			}
		}
	}
	
	/**
	 * Show all the computed smell statistics 
	 * 
	 * @return statistics about smells in formatted string
	 */
	public String showSmellStatistics() {
		return smellStatistics.toString();
	}
	
	/**
	 * Used to generate text files based on the report for abuser, bloater, couplers and dispensables
	 */
	public void generateTextFileReport() {
		//===================Type of Smell Categories
		List<Map<String, Map<Abusers, Smell>>> abuses = abuser.getAbusers();
		List<Map<String, Map<Bloaters, Smell>>> bloats = bloater.getBloaters();
		List<Map<String, Map<Couplers, Smell>>> couples = coupler.getCouplers();
		List<Map<String, Map<Dispensables, Smell>>> dispenses = dispensable.getDispensables();
		
		//PrintWriter writer = new PrintWriter("File name", "type")
		//CREATE A FILE FOR ABUSER, BLOATER, COUPLER AND DISPENSABLE
		//===================ABUSERS
		for(Map<String,Map<Abusers,Smell>> map: abuses) {
			for(String fileName: map.keySet()) {	//FileName 
				for(Abusers abuse: map.get(fileName).keySet()) {	//Enum 
						if(!map.get(fileName).get(abuse).getIssue().isEmpty()) { //Dont print things that are empty
							System.out.println(fileName + " " + map.get(fileName).get(abuse).toString(abuse.toString()));
						}
				}
			}
		}
		
		//===================BLOATERS
		for(Map<String,Map<Bloaters,Smell>> map: bloats) {
			for(String fileName: map.keySet()) {	//FileName 
				for(Bloaters bloat: map.get(fileName).keySet()) {	//Enum 
						if(!map.get(fileName).get(bloat).getIssue().isEmpty()) { //Dont print things that are empty
							System.out.println(fileName + " " + map.get(fileName).get(bloat).toString(bloat.toString()));
						}
				}
			}
		}
		
		//===================COUPLERS
		for(Map<String,Map<Couplers,Smell>> map: couples) {
			for(String fileName: map.keySet()) {	//FileName 
				for(Couplers couple: map.get(fileName).keySet()) {	//Enum 
						if(!map.get(fileName).get(couple).getIssue().isEmpty()) { //Dont print things that are empty
							System.out.println(fileName + " " + map.get(fileName).get(couple).toString(couple.toString()));
						}
				}
			}
		}
		
		//===================DISPENSABLES
		for(Map<String,Map<Dispensables,Smell>> map: dispenses) {
			for(String fileName: map.keySet()) {	//FileName 
				for(Dispensables dispense: map.get(fileName).keySet()) {	//Enum 
						if(!map.get(fileName).get(dispense).getIssue().isEmpty()) { //Dont print things that are empty
							System.out.println(fileName + " " + map.get(fileName).get(dispense).toString(dispense.toString()));
						}
				}
			}
		}
	}
	
	
}
