package detector;

import java.util.Map;

import smell.Smell;
import smell.Smell.Abusers;
import smell.Smell.Bloaters;
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
	 * @return String representation of all computed statistics
	 */
	@Override
	public String toString() {
		String statsStr = "____SMELL STATISTICS____\n\n";
		statsStr += "Total number of times an OOPAbuser smell was detected in the entire project:  " + this.computeNumTimesOOPAbuserDetectedInProject() + "\n";
		statsStr += "Total number of times a Bloater smell was detected in the entire project:  " + this.computeNumTimesBloaterDetectedInProject() + "\n";
		return statsStr;
	}
}
