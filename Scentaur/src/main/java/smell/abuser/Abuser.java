package smell.abuser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import smell.Detectable;
import smell.Smell;
import smell.Smell.Abusers;

/**
 * A class that detects abusers within a project filled with Java files
 */
public class Abuser implements Detectable<Abusers>{
	
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

	/**
	 * Returns the map based on the fileName if it exists
	 */
	public Map<Abusers, Smell> getMapUsingFileName(String fileName) {
		
		Map<Abusers, Smell> fileNameAbuser = new HashMap<Abusers, Smell>();
		
		for(Map<String, Map<Abusers, Smell>> abuse :abusers) {
			if(abuse.containsKey(fileName)) {
				fileNameAbuser = abuse.get(fileName);
				break;
			}
		}
		
		return fileNameAbuser;
	}
	
	/**
	 * Return the set of file names within this smell category
	 */
	public List<String> getFileNames(){
		List<String> fileName = new ArrayList<>();
		
		for(Map<String, Map<Abusers, Smell>> abuse: abusers) {
			fileName.addAll(abuse.keySet());
		}
		return fileName;
	}
}
