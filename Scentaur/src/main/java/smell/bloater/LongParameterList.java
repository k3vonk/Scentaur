package smell.bloater;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;

public class LongParameterList extends Bloater {

    public static final int PARAMETER_THRESHOLD = 3;
    
    public LongParameterList() {
    	smell = new ArrayList<>();
    }
    
    /**
     * Visits each compilation unit, checking its method parameter is longer than 2
     * @return return a Detect object if true, else null
     */
    public void visit(MethodDeclaration n, Void args) {
    	int numParams = n.getParameters().size();

        if (numParams >= PARAMETER_THRESHOLD) {
            smell.add(n);
            addComment(n);
        }
    }

    /**
     * Add a comment above a method to indicate that its a smell
     */
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