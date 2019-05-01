package smell.bloater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

import smell.Detectable;
import smell.Smell;

/**
 * A class that detects bloaters within a project filled with Java files
 */
public class Bloater implements Detectable{
	
	public enum Bloaters{ //Types of Bloaters
		LARGE_CLASS, LONG_METHOD, LONG_PARAMETER_LIST, PRIMITIVE_OBSESSION;
	}
	
	private List<Map<String, Map<Bloaters, Smell>>> bloaters;
	
	public Bloater() {
		bloaters = new ArrayList<Map<String, Map<Bloaters, Smell>>>();
	}
	
	/**
	 * Detects the different bloater smells in all the compilation units
	 * @param cu
	 */
	public void detect(List<CompilationUnit> cu) {
		
		for(CompilationUnit c: cu) {
			Map<Bloaters, Smell> c_bloats = new HashMap<Bloaters, Smell>();
			//-----------Large Class
			Smell largeClass = new LargeClass();
			c.accept(largeClass, null);	
			c_bloats.put(Bloaters.LARGE_CLASS, largeClass);
			
			//-----------LongMethod
			Smell longMethod = new LongMethod();
			c.accept(longMethod, null);
			c_bloats.put(Bloaters.LONG_METHOD, longMethod);
			
			//-----------LongParameterList
			Smell longParam = new LongParameterList();
			c.accept(longParam, null);
			c_bloats.put(Bloaters.LONG_PARAMETER_LIST, longParam);
			
			//-----------PrimitiveObsession
			Smell primObsession = new PrimitiveObsession();
			c.accept(primObsession, null);
			c_bloats.put(Bloaters.PRIMITIVE_OBSESSION, primObsession);
			
			//-----------Add to list of bloaters
			Map<String, Map<Bloaters, Smell>> map = new HashMap<String, Map<Bloaters, Smell>>();
			map.put(c.getType(0).getNameAsString(), c_bloats);
			bloaters.add(map);
		}
		
	}
	
	/**
	 * System out checks
	 */
	public void systemOutBloaters() {
		for(Map<String, Map<Bloaters, Smell>> map: bloaters) {
			for(Map<Bloaters,Smell> m: map.values()) {
				for(Bloaters b: m.keySet()) {
					System.out.println(b + " " + m.get(b).getIssue());
				}
			}
		}
	}
}
