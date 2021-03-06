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
 * A class that detects smells for the different smell categories
 */

public class Detector {
	
	//Different smell category
	public Abuser abuser = new Abuser();
	public Bloater bloater = new Bloater();
	public Coupler coupler = new Coupler();
	public Dispensable dispensable = new Dispensable();
	
	/**
	 * Detects smell for a specific list of compilation units
	 */
	public void detect(List<CompilationUnit> all) {
		abuser.detect(all);
		bloater.detect(all);
		coupler.detect(all);
		dispensable.detect(all);
	}
	
	/**
	 * @return a string representation of a specific abuser smell
	 */
	public String getAbuserSmell(int index, String fileName) {
		
		List<Map<String,Map<Abusers,Smell>>> abuses = this.abuser.getAbusers();
		Map<Abusers,Smell> map = abuses.get(index).get(fileName);
		String res = "";
			for(Abusers key: map.keySet()) {	
				if(!map.get(key).getIssue().isEmpty()) { 					
					res += map.get(key).toString(key.toString());
				}
			}
			if(res.isEmpty()) {
				return null;
			}
		return res;
	}
	
	/**
	 * @return a string representation of a specific bloater smell
	 */
	public String getBloaterSmell(int index, String fileName) {
		
		List<Map<String,Map<Bloaters,Smell>>> bloats = this.bloater.getBloaters();
		Map<Bloaters,Smell> map = bloats.get(index).get(fileName);
		String res = "";
			for(Bloaters key: map.keySet()) {	
				if(!map.get(key).getIssue().isEmpty()) { 					
					res += map.get(key).toString(key.toString());
				}
			}
			if(res.isEmpty()) {
				return null;
			}
		return res;
	}

	/**
	 * @return a string representation of a specific couplers smell
	 */
	public String getCouplerSmell(int index, String fileName) {
		
		List<Map<String,Map<Couplers,Smell>>> couplers = this.coupler.getCouplers();
		Map<Couplers,Smell> map = couplers.get(index).get(fileName);
		String res = "";
			for(Couplers key: map.keySet()) {	
				if(!map.get(key).getIssue().isEmpty()) { 					
					res += map.get(key).toString(key.toString());
				}
			}
			if(res.isEmpty()) {
				return null;
			}
		return res;
	}
	
	/**
	 * @return a string representation of a specific dispensable smell
	 */
	public String getDispensableSmell(int index, String fileName) {
		
		List<Map<String,Map<Dispensables,Smell>>> dispensables = this.dispensable.getDispensables();
		Map<Dispensables,Smell> map = dispensables.get(index).get(fileName);
		String res = "";
			for(Dispensables key: map.keySet()) {	
				if(!map.get(key).getIssue().isEmpty()) { 					
					res += map.get(key).toString(key.toString());
				}
			}
			if(res.isEmpty()) {
				return null;
			}
		return res;
	}
	
}
