package smell;

import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public abstract class Smell extends VoidVisitorAdapter<Void> implements Smellable{
	
	protected List<Node> smell;
	
	/**
	 * Obtain the fileName of the node
	 */
	public String getFileName(Node n) {
		return n.getParentNode().get().findCompilationUnit().get().getStorage().get().getFileName();
	}
	
	public List<Node> getIssue() {
		return smell;
	}
	
	public void clear() {
		smell.clear();
	}
	
}
