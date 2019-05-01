package smell.dispensables;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;

import smell.Smell;


public class DataClass extends Smell {


    public DataClass() {
        smell = new ArrayList<>();
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
        Boolean foundUsefulMethod = false;

        // Check if the class has a method that's not a getter/setter/toString
        for (MethodDeclaration m : n.getMethods()) {
            if (!isToString(m) && !isSetter(m) && !isGetter(m)) {
                foundUsefulMethod = true;
            }
        }
        // Couldn't find a non getter/set/tostring so is prolly a dataclass
        if (!foundUsefulMethod) {
            smell.add(n);
        }
    }

    private boolean isToString(MethodDeclaration method) {
        return method.getNameAsString().equals("toString");
    }

    /**
     * Checks if a given method is a getter through various steps.
     * @param method
     * @return
     */
    private boolean isGetter(MethodDeclaration method) {
        if (method.getParameters().size() != 0) {
            return false;
        }
        if (method.getType().isVoidType()) {
            return false;
        }
        if (!method.isPublic()) {
            return false;
        }
        if (!method.getNameAsString().startsWith("get") && !method.getNameAsString().startsWith(
                "Get")) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a given method is a setter.
     * @param method
     * @return
     */
    private boolean isSetter(MethodDeclaration method) {
        if (method.getParameters().size() != 1) {
            return false;
        }
        if (!method.getNameAsString().startsWith("set") && !method.getNameAsString().startsWith(
                "Set")) {
            return false;
        }
        if (!method.getType().isVoidType()) {
            return false;
        }
        if (!method.isPublic()) {
            return false;
        }
        return true;
    }


    @Override
    public void addComment(Node n) {

        //Check if has no comments
        if (!n.getComment().isPresent()) {
            ((NodeWithJavadoc<ConstructorDeclaration>) n).setJavadocComment(
                    "Possible SmellDetected: " + this.getClass().getSimpleName());
        } else {
            Comment comment = n.getComment().get();
            ((NodeWithJavadoc<ConstructorDeclaration>) n).setJavadocComment(
                    comment.getContent() + "\n\nPossible SmellDetected: "
                            + this.getClass().getSimpleName());
        }
    }

}
