package smell.coupler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import smell.Detectable;
import smell.Smell;
import smell.Smell.Couplers;

/**
 * A class that detects couplers within a project filled with Java files
 */
public class Coupler implements Detectable<Couplers>{

	
	private List<Map<String, Map<Couplers, Smell>>> couplers;
	
	public Coupler() {
		couplers = new ArrayList<Map<String, Map<Couplers, Smell>>>();
	}
	
	/**
	 * Detects the different coupler smells in all the compilation units
	 * @param cu
	 */
	public void detect(List<CompilationUnit> cu) {
		
		for(CompilationUnit c: cu) {
			Map<Couplers, Smell> c_couple = new HashMap<Couplers, Smell>();
			
			//-----------FeatureEnvy 
			Smell featureEnvy = new FeatureEnvy();
			c.accept(featureEnvy, null);	
			c_couple.put(Couplers.FEATURE_ENVY, featureEnvy);
			
			//-----------MessageChain
			Smell messageChain = new MessageChain();
			c.accept(messageChain, null);
			c_couple.put(Couplers.MESSAGE_CHAIN, messageChain);
			
			//-----------MiddleMan
			Smell middleMan = new MiddleMan();
			c.accept(middleMan, null);
			c_couple.put(Couplers.MIDDLE_MAN, middleMan);
			
			//-----------Add to list of bloaters
			Map<String, Map<Couplers, Smell>> map = new HashMap<String, Map<Couplers, Smell>>();
			map.put(c.getType(0).getNameAsString(), c_couple);
			couplers.add(map);
		}
		
	}
	
	/**
	 * Getter for list of couplers
	 * @return
	 */
	public List<Map<String, Map<Couplers, Smell>>> getCouplers(){
		return couplers;
	}

	/**
	 * Returns the map based on the fileName if it exists
	 */
	public Map<Couplers, Smell> getMapUsingFileName(String fileName) {
		Map<Couplers, Smell> fileNameCoupler = new HashMap<Couplers, Smell>();
		
		for(Map<String, Map<Couplers, Smell>> couple :couplers) {
			if(couple.containsKey(fileName)) {
				fileNameCoupler = couple.get(fileName);
				break;
			}
		}
		
		return fileNameCoupler;
	}

}
