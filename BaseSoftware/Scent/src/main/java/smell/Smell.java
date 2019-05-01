package smell;

import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * An abstract Smell class that allows for plug-and-play for more concrete smell ideas
 *
 */
public abstract class Smell extends VoidVisitorAdapter<Void> implements Smellable{
	
	public enum Smells{
		BLOATERS, OOP_ABUSERS, COUPLERS, DISPENSABLES;
	}
	
	public enum Abusers{ //Types of Abuser
		DATA_HIDING, SWITCH_STATEMENT;
	}
	
	public enum Bloaters{ //Types of Bloaters
		LARGE_CLASS, LONG_METHOD, LONG_PARAMETER_LIST, PRIMITIVE_OBSESSION;
	}
	
	public enum Couplers{ //Types of Couplers
		FEATURE_ENVY, MESSAGE_CHAIN, MIDDLE_MAN;
	}
	
	public enum Dispensables{ //Types of Dispensables
		COMMENTS, DATA_CLASS, DEAD_CODE, LAZY_CLASS;
	}
	
	protected List<Node> smell;
	
	/**
	 * Obtain the fileName of the node
	 */
	public String getFileName(Node n) {
		return n.getParentNode().get().findCompilationUnit().get().getStorage().get().getFileName();
	}
	
	/**
	 * @return - the list of nodes that correlate to the smell
	 */
	public List<Node> getIssue() {
		return smell;
	}
	
	public void clear() {
		smell.clear();
	}
	
	/**
	 * Returns the string of the smell list
	 */
	public String toString(String type) {
		String code = "";
		if(!smell.isEmpty()) {
			for(Node node: smell) {
				String lineNumber = node.getBegin().get().line + "";
				String javaCode = node.toString();
				code += type + " list at line: "+ lineNumber + "\n" + javaCode + "\n\n";
			}
		}
		
		return code;
	}
	
}
