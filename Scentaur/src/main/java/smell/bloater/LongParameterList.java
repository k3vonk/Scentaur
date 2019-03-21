package smell.bloater;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;
import smell.Smell;

public class LongParameterList extends GenericVisitorAdapter<Smell, Void> {

    public static final int PARAMETER_THRESHOLD = 3;
    private List<MethodDeclaration> longParameterMethods;

    public LongParameterList() {
        longParameterMethods = new ArrayList<>();
    }

    /**
     * @return a list of MethodDeclarations that has 3 or more parameters
     */
    public List<MethodDeclaration> get() {
        return longParameterMethods;
    }

    /**
     * Visits each compilation unit, checking its method parameter is longer than 2
     */

    public Smell visit(MethodDeclaration n, Void args) {
        int numParams = n.getParameters().size();

        if (numParams >= PARAMETER_THRESHOLD) {
            longParameterMethods.add(n);
            addComment(n);


            System.out.println(n.getName());
            return new Smell(
                    n.getParentNode().get().findCompilationUnit().get().getStorage().get()
                            .getFileName(),
                    n.getName().toString(), n.getBegin().get().line,

                    "Long Parameter List", "Has too many parameters in method declaration");


        }


        return null;
    }

    /**
     * Add a comment above a method to indicate that its a smell
     */
    private void addComment(MethodDeclaration n) {
        Comment comment = n.getComment().get();
        n.setJavadocComment(
                comment.getContent() + "\n\nSmellDetected: " + this.getClass().getSimpleName());
    }

}