package smell.dispensable;
import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;

import smell.Smell;

/**
 * Detects class that doesn't do much. I.e. classes should earn your attention otherwise it isn't useful
 *
 */
public class LazyClass extends Smell {

    public static final int LAZY_MIN_INTERACTION = 3;

    public LazyClass() {
        smell = new ArrayList<>();
    }

    /**
     * Visits classes and checks under conditions to evaluate if the class is a smell
     */
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        if(n.getMethods().size() <= LAZY_MIN_INTERACTION && n.findAll(BlockStmt.class).size() <= LAZY_MIN_INTERACTION && !n.isAbstract()){
           smell.add(n);
        }
    }



    @Override
    public void addComment(Node n) {

        /*//Check if has no comments
        if(!n.getComment().isPresent()) {
            ((NodeWithJavadoc<ConstructorDeclaration>) n).setJavadocComment("Possible SmellDetected: " + this.getClass().getSimpleName());
        }else {
            Comment comment = n.getComment().get();
            ((NodeWithJavadoc<ConstructorDeclaration>) n).setJavadocComment(
                    comment.getContent() + "\n\nPossible SmellDetected: " + this.getClass().getSimpleName());
        }*/
    }

}
