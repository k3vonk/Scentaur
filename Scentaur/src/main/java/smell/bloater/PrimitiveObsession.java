package smell.bloater;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;

public class PrimitiveObsession extends Bloater{
	public static final int PRIMITIVES_THRESHOLD = 0;
	
	// Constructor
	public PrimitiveObsession() {
    	smell = new ArrayList<>();
    }
	
	/**
     * Visits each ClassOrInterfaceDeclaration for a Compilation Unit checking its
     * number of primitive fields are below the threshold.
     */
	@Override
	public void visit(ClassOrInterfaceDeclaration cOri, Void arg) {
		int numPrimitives = 0;
		List<FieldDeclaration> classFields = cOri.getFields();
		for(FieldDeclaration f : classFields) if(f.getElementType().isPrimitiveType()) numPrimitives++;
		if(numPrimitives >= PRIMITIVES_THRESHOLD) {
			smell.add(cOri);
			addComment(cOri);
		}
	}

	/**
     * Add a comment above a method to indicate that its a smell
     */
	@Override
	public void addComment(Node n) {
		ClassOrInterfaceDeclaration cOri = (ClassOrInterfaceDeclaration) n;
		
		if(!cOri.getComment().isPresent()) {
			cOri.setJavadocComment("SmellDetected: " + this.getClass().getSimpleName());
		}else {
			Comment comment = cOri.getComment().get();
			cOri.setJavadocComment(
                comment.getContent() + "\n\nSmellDetected: " + this.getClass().getSimpleName());
		}
	}
}
