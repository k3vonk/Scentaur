package smell.bloater;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import smell.Smell;

/**
 * Detects smells for the following:
 * 1. When small objects should be primitives
 * 2. Use of constants inappropriately
 * 3. Use of string constants as field names for use in data arrays
 *
 */
public class PrimitiveObsession extends Smell{
	public static final int PRIMITIVES_THRESHOLD = 4;
	
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
			//addComment(cOri);
		}
	}

	/**
     * Add a comment above a method to indicate that its a smell
     */
	@Override
	public void addComment(Node n) {
		/*ClassOrInterfaceDeclaration cOri = (ClassOrInterfaceDeclaration) n;
		
		if(!cOri.getComment().isPresent()) {
			cOri.setJavadocComment("SmellDetected: " + this.getClass().getSimpleName());
		}else {
			Comment comment = cOri.getComment().get();
			cOri.setJavadocComment(
                comment.getContent() + "\n\nSmellDetected: " + this.getClass().getSimpleName());
		}*/
	}
}
