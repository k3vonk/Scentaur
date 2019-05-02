package detector;

import java.util.Map;

import smell.Smell;
import smell.Smell.Abusers;
import smell.Smell.Bloaters;
import smell.Smell.Couplers;
import smell.Smell.Dispensables;
import smell.abuser.Abuser;
import smell.bloater.Bloater;
import smell.coupler.Coupler;
import smell.dispensable.Dispensable;

public class Statistics {
	// Attributes
	private Abuser abusers;
	private Bloater bloaters;
	private Coupler couplers;
	private Dispensable dispensables;
	
	// Constructor
	public Statistics(Abuser abuser, Bloater bloater, Coupler coupler, Dispensable dispensable) {
		 this.abusers = abuser;
		 this.bloaters = bloater;
		 this.couplers = coupler;
		 this.dispensables = dispensable;
	}
	
	/**
	 * Computes total number of times an OOPAbuser smell was detected in 
	 * entire project.
	 * 
	 * @return numTimesOOPAbuserDetected
	 */
	public int computeNumTimesOOPAbuserDetectedInProject() {
		int numTimesOOPAbuserDetected = 0;
		//Iterate over each type of OOPAbuser smell
		for(Map<String,Map<Abusers,Smell>> map: abusers.getAbusers()) {
			//Iterate over file names this OOPAbuser was detected
			for(String fileName: map.keySet()) {	 
				for(Abusers abuse: map.get(fileName).keySet()) {	
					numTimesOOPAbuserDetected += map.get(fileName).get(abuse).getIssue().size();
				}
			}
		}
		return numTimesOOPAbuserDetected;
	}
	
	/**
	 * Computes total number of times an Bloater smell was detected in 
	 * entire project.
	 * 
	 * @return numTimesBloaterDetected
	 */
	public int computeNumTimesBloaterDetectedInProject() {
		int numTimesBloaterDetected = 0;
		//Iterate over each type of OOPAbuser smell
		for(Map<String, Map<Bloaters, Smell>> map: bloaters.getBloaters()) {
			//Iterate over file names this OOPAbuser was detected
			for(String fileName: map.keySet()) {	 
				for(Bloaters bloater: map.get(fileName).keySet()) {	
					numTimesBloaterDetected += map.get(fileName).get(bloater).getIssue().size();
				}
			}
		}
		return numTimesBloaterDetected;
	}
	
	/**
	 * Computes total number of times a Couper smell was detected in 
	 * entire project.
	 * 
	 * @return numTimesCouplerDetected
	 */
	public int computeNumTimesCouplerDetectedInProject() {
		int numTimesCouplerDetected = 0;
		//Iterate over each type of OOPAbuser smell
		for(Map<String, Map<Couplers, Smell>> map: couplers.getCouplers()) {
			//Iterate over file names this OOPAbuser was detected
			for(String fileName: map.keySet()) {	 
				for(Couplers coupler: map.get(fileName).keySet()) {	
					numTimesCouplerDetected += map.get(fileName).get(coupler).getIssue().size();
				}
			}
		}
		return numTimesCouplerDetected;
	}
	
	/**
	 * Computes total number of times a Dispensable smell was detected in 
	 * entire project.
	 * 
	 * @return numTimesDispensableDetected
	 */
	public int computeNumTimesDispensableDetectedInProject() {
		int numTimesDispensableDetected = 0;
		//Iterate over each type of OOPAbuser smell
		for(Map<String, Map<Dispensables, Smell>> map: dispensables.getDispensables()) {
			//Iterate over file names this OOPAbuser was detected
			for(String fileName: map.keySet()) {	 
				for(Dispensables dispensable: map.get(fileName).keySet()) {	
					numTimesDispensableDetected += map.get(fileName).get(dispensable).getIssue().size();
				}
			}
		}
		return numTimesDispensableDetected;
	}
	
	/**
	 * @return String representation of all computed statistics
	 */
	@Override
	public String toString() {
		String statsStr = "____SMELL STATISTICS____\n\n";
		statsStr += "Total number of times an OOPAbuser smell was detected in the entire project:  " + this.computeNumTimesOOPAbuserDetectedInProject() + "\n";
		statsStr += "Total number of times a Bloater smell was detected in the entire project:  " + this.computeNumTimesBloaterDetectedInProject() + "\n";
		statsStr += "Total number of times a Coupler smell was detected in the entire project:  " + this.computeNumTimesCouplerDetectedInProject() + "\n";
		statsStr += "Total number of times a Dispensable smell was detected in the entire project:  " + this.computeNumTimesDispensableDetectedInProject() + "\n";
		return statsStr;
	}
}
