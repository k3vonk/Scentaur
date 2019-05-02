package detector;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
	private static NumberFormat formatter = new DecimalFormat("#0.00");	

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
		//Iterate over each type of Bloater smell
		for(Map<String, Map<Bloaters, Smell>> map: bloaters.getBloaters()) {
			//Iterate over file names this Bloater was detected
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
		//Iterate over each type of Coupler smell
		for(Map<String, Map<Couplers, Smell>> map: couplers.getCouplers()) {
			//Iterate over file names this Coupler was detected
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
		//Iterate over each type of Dispensable smell
		for(Map<String, Map<Dispensables, Smell>> map: dispensables.getDispensables()) {
			//Iterate over file names this Dispensable was detected
			for(String fileName: map.keySet()) {	 
				for(Dispensables dispensable: map.get(fileName).keySet()) {	
					numTimesDispensableDetected += map.get(fileName).get(dispensable).getIssue().size();
				}
			}
		}
		return numTimesDispensableDetected;
	}
	
	/**
	 * Computes total number of times a smell was detected in 
	 * entire project.
	 * 
	 * @return numTimesSmellDetected
	 */
	public int computeNumTimesSmellDetectedInProject() {
		return this.computeNumTimesBloaterDetectedInProject() + this.computeNumTimesCouplerDetectedInProject() + this.computeNumTimesDispensableDetectedInProject() + this.computeNumTimesOOPAbuserDetectedInProject();
	}
	
	/**
	 * Computes proportion of project where OOPAbuser detected.
	 * 
	 * @return numTimesOOPAbuserDetected / numTimesSmellDetected
	 */
	public float proportionProjectOOPAbuserDetected() {
		return ((float) this.computeNumTimesOOPAbuserDetectedInProject() / this.computeNumTimesSmellDetectedInProject()) * 100;
	}
	
	/**
	 * Computes proportion of project where Coupler detected.
	 * 
	 * @return numTimesCouplerDetected / numTimesSmellDetected
	 */
	public float proportionProjectCouplerDetected() {
		return ((float) this.computeNumTimesCouplerDetectedInProject() / this.computeNumTimesSmellDetectedInProject()) * 100;
	}
	
	/**
	 * Computes proportion of project where Bloater detected.
	 * 
	 * @return numTimesBloaterDetected / numTimesSmellDetected
	 */
	public float proportionProjectBloaterDetected() {
		return ((float) this.computeNumTimesBloaterDetectedInProject() / this.computeNumTimesSmellDetectedInProject()) * 100;
	}
	
	/**
	 * Computes proportion of project where Dispensable detected.
	 * 
	 * @return numTimesBloaterDetected / numTimesSmellDetected
	 */
	public float proportionProjectDispensableDetected() {
		return ((float) this.computeNumTimesDispensableDetectedInProject() / this.computeNumTimesSmellDetectedInProject()) * 100;
	}
	
	/**
	 * @return String representation of all computed statistics
	 */
	@Override
	public String toString() {
		String statsStr = "____SMELL STATISTICS____\n\n";
		statsStr += "Total number of times a smell was detected in the entire project:  " + this.computeNumTimesSmellDetectedInProject() + "\n";
		statsStr += "Total number of times an OOPAbuser smell was detected in the entire project:  " + this.computeNumTimesOOPAbuserDetectedInProject() + "\n";
		statsStr += "Total number of times a Bloater smell was detected in the entire project:  " + this.computeNumTimesBloaterDetectedInProject() + "\n";
		statsStr += "Total number of times a Coupler smell was detected in the entire project:  " + this.computeNumTimesCouplerDetectedInProject() + "\n";
		statsStr += "Total number of times a Dispensable smell was detected in the entire project:  " + this.computeNumTimesDispensableDetectedInProject() + "\n";
		statsStr += "Proportion of entire project where OOPAbuser was detected: " + formatter.format(this.proportionProjectOOPAbuserDetected()) + "%\n";
		statsStr += "Proportion of entire project where Coupler was detected: " + formatter.format(this.proportionProjectCouplerDetected()) + "%\n";
		statsStr += "Proportion of entire project where Bloater was detected: " + formatter.format(this.proportionProjectBloaterDetected()) + "%\n";
		statsStr += "Proportion of entire project where Dispensable was detected: " + formatter.format(this.proportionProjectDispensableDetected()) + "%\n";
		return statsStr;
	}
}
