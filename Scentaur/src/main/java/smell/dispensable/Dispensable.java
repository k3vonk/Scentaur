package smell.dispensable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import smell.Detectable;
import smell.Smell;
import smell.Smell.Dispensables;

/**
 * A class that detects dispensables within a project filled with Java files
 */
public class Dispensable implements Detectable<Dispensables>{
	
	private List<Map<String, Map<Dispensables, Smell>>> dispensables;
	
	public Dispensable() {
		dispensables = new ArrayList<Map<String, Map<Dispensables, Smell>>>();
	}
	
	/**
	 * Detects the different dispensable smells in all the compilation units
	 * @param cu
	 */
	public void detect(List<CompilationUnit> cu) {
		
		for(CompilationUnit c: cu) {
			Map<Dispensables, Smell> c_dispense = new HashMap<Dispensables, Smell>();
			
			//-----------Comments
			Smell comments = new Comments();
			c.accept(comments, null);	
			c_dispense.put(Dispensables.COMMENTS, comments);
			
			//-----------DataClass
			Smell dataClass = new DataClass();
			c.accept(dataClass, null);
			c_dispense.put(Dispensables.DATA_CLASS, dataClass);
			
			//-----------DeadCode
			/*Smell deadCode = new DeadCode();
			c.accept(deadCode, null);
			c_dispense.put(Dispensables.DEAD_CODE, deadCode);
			*/
			//-----------LazyClass
			Smell lazyClass = new LazyClass();
			c.accept(lazyClass, null);
			c_dispense.put(Dispensables.LAZY_CLASS, lazyClass);
			
			//-----------Add to list of bloaters
			Map<String, Map<Dispensables, Smell>> map = new HashMap<String, Map<Dispensables, Smell>>();
			map.put(c.getType(0).getNameAsString(), c_dispense);
			dispensables.add(map);
		}
		
	}
	
	/**
	 * Getter for list of dispensables
	 * @return
	 */
	public List<Map<String, Map<Dispensables, Smell>>> getDispensables(){
		return dispensables;
	}

	/**
	 * Returns the map based on the fileName if it exists
	 */
	public Map<Dispensables, Smell> getMapUsingFileName(String fileName) {
		Map<Dispensables, Smell> fileNameDispense = new HashMap<Dispensables, Smell>();
		
		for(Map<String, Map<Dispensables, Smell>> dispense :dispensables) {
			if(dispense.containsKey(fileName)) {
				fileNameDispense = dispense.get(fileName);
				break;
			}
		}
		
		return fileNameDispense;
	}
	
	/**
	 * Return the set of file names within this smell category
	 */
	public List<String> getFileNames(){
		List<String> fileName = new ArrayList<>();
		
		for(Map<String, Map<Dispensables, Smell>> dispense: dispensables) {
			fileName.addAll(dispense.keySet());
		}
		return fileName;
	}
}
