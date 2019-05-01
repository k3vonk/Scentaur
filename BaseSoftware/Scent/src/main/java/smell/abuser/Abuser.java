package smell.abuser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import smell.Detectable;
import smell.Smell;
import smell.dispensable.Dispensable.Dispensables;

/**
 * A class that detects abusers within a project filled with Java files
 */
public class Abuser implements Detectable{
	
	public enum Abusers{ //Types of Abuser
		DATA_HIDING, SWITCH_STATEMENT;
	}
	
	private List<Map<String, Map<Abusers, Smell>>> abusers;
	
	public Abuser() {
		abusers = new ArrayList<Map<String, Map<Abusers, Smell>>>();
	}
	
	/**
	 * Detects the different abusers smells in all the compilation units
	 * @param cu
	 */
	public void detect(List<CompilationUnit> cu) {
		
		for(CompilationUnit c: cu) {
			Map<Abusers, Smell> c_abuse = new HashMap<Abusers, Smell>();
			
			//-----------DataHiding 
			Smell dataHiding = new DataHiding();
			c.accept(dataHiding, null);	
			c_abuse.put(Abusers.DATA_HIDING, dataHiding);
			
			//-----------MessageChain
			Smell switchStatement = new SwitchStatement();
			c.accept(switchStatement, null);
			c_abuse.put(Abusers.SWITCH_STATEMENT, switchStatement);
			
			//-----------Add to list of bloaters
			Map<String, Map<Abusers, Smell>> map = new HashMap<String, Map<Abusers, Smell>>();
			map.put(c.getType(0).getNameAsString(), c_abuse);
			abusers.add(map);
		}
		
	}
	
	/**
	 * Getter for list of abusers
	 * @return
	 */
	public List<Map<String, Map<Abusers, Smell>>> getAbusers(){
		return abusers;
	}
}
