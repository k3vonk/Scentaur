package smell.bloater;

import java.util.ArrayList;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;


public class LongMethod extends Bloater{
	
	public static final int METHOD_LENGTH_THRESHOLD = 10;
	
	public LongMethod() {
		smell = new ArrayList<>();
	}
	
	/**
	 * Visits each MethodDeclaration for a Compilation Unit checking its 
	 * body length is below the method length threshold.
     */
	public void visit(MethodDeclaration n, Void args) {
		String[] lines = n.getBody().toString().split("\r\n|\r|\n");
		if(lines.length >= METHOD_LENGTH_THRESHOLD) {
			smell.add(n);
			addComment(n);
		}
	}

	@Override
	public void addComment(Node n) {
		MethodDeclaration md = (MethodDeclaration) n;
		
		//Check if has no comments
		if(!md.getComment().isPresent()) {
			md.setJavadocComment("SmellDetected: " + this.getClass().getSimpleName());
		}else {
			Comment comment = md.getComment().get();
			md.setJavadocComment(
                comment.getContent() + "\n\nSmellDetected: " + this.getClass().getSimpleName());
		}
	}
}
