package smell.bloater;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;

public class LargeClass extends Bloater{
	
	public static final int LINES_THRESHOLD = 110;
	
	public LargeClass() {
		smell = new ArrayList<>();
	}
	
	@Override
	public void visit(ClassOrInterfaceDeclaration n, Void arg) {
		int length = 1;
        for (Node child : n.getMembers()){
            if (child instanceof FieldDeclaration) {
                length += 1;
            }
            else if (child instanceof ConstructorDeclaration){
                length += child.getRange().get().getLineCount();
            }
            else if (child instanceof MethodDeclaration){
                length += child.getRange().get().getLineCount();
            }
        }
        if (length >= LINES_THRESHOLD) {
            smell.add(n);
            addComment(n);
        }
	}

	@Override
	public void addComment(Node n) {
		
		//Check if has no comments
		if(!n.getComment().isPresent()) {
			((NodeWithJavadoc<ConstructorDeclaration>) n).setJavadocComment("SmellDetected: " + this.getClass().getSimpleName());
		}else {
			Comment comment = n.getComment().get();
			((NodeWithJavadoc<ConstructorDeclaration>) n).setJavadocComment(
                comment.getContent() + "\n\nSmellDetected: " + this.getClass().getSimpleName());
		}
	}

}
