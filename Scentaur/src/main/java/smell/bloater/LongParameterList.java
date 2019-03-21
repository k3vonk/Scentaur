package smell.bloater;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class LongParameterList extends VoidVisitorAdapter<Void> {

	public static final int PARAMETER_THRESHOLD = 3;
    private List<MethodDeclaration> longParameterMethods;
   
    public LongParameterList(){
        longParameterMethods = new ArrayList<>();
    }

    /**
     * @return a list of MethodDeclarations that has 3 or more parameters
     */
    public List<MethodDeclaration> get(){
        return longParameterMethods;
    }

    /**
     * Visits each compilation unit, checking its method parameter is longer than 2
     */
    @Override
    public void visit(MethodDeclaration n, Void args){
        int numParams = n.getParameters().size();
        
        if (numParams >= PARAMETER_THRESHOLD) {
            longParameterMethods.add(n);
            addComment(n);   
        }

    }
    
    /**
     * Add a comment above a method to indicate that its a smell
     * @param n
     */
    private void addComment(MethodDeclaration n) {
    	Comment comment = n.getComment().get();
    	n.setJavadocComment(comment.getContent() + "\n\nSmellDetected: " + this.getClass().getSimpleName());
    }
    
}