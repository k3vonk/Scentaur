package smell.bloater;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;

import smell.Smell;

/**
 * A class that detects smell within methods that contains too many lines of code
 *
 */
public class LongMethod extends Smell{
	
	public static final int METHOD_LENGTH_THRESHOLD = 10;
	
	public LongMethod() {
		smell = new ArrayList<>();
	}
	
	/**
	 * Visits each MethodDeclaration for a Compilation Unit checking its 
	 * body length is below the method length threshold.
     */
	public void visit(MethodDeclaration n, Void args) {
		int lines = n.findAll(Statement.class).size();

		if(lines >= METHOD_LENGTH_THRESHOLD) {
			smell.add(n);
		}
	}

	@Override
	public void addComment(Node n) {
		/*MethodDeclaration md = (MethodDeclaration) n;
		
		//Check if has no comments
		if(!md.getComment().isPresent()) {
			md.setJavadocComment("SmellDetected: " + this.getClass().getSimpleName());
		}else {
			Comment comment = md.getComment().get();
			md.setJavadocComment(
                comment.getContent() + "\n\nSmellDetected: " + this.getClass().getSimpleName());
		}*/
	}
}
