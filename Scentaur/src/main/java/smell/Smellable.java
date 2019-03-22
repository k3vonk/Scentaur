package smell;

import com.github.javaparser.ast.Node;

/**
 * Contains behaviours of all code smells
 */
public interface Smellable {

	public String getFileName(Node n); 
	public void addComment(Node n); 	// Add comment to a node
}
