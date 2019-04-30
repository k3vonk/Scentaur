package smell.coupler;
import java.util.ArrayList;
import java.util.List;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import smell.Smell;

public class InappropriateIntimacy extends Smell {
	public String error;
	public InappropriateIntimacy() {
		smell = new ArrayList<>();
	}
	
	/**
	 * Method to check if class has encapsulation issues e.g. allowing
	 * Clients to access its internals (attribues should be private)
	 */
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
    	List<FieldDeclaration> classFields = n.getFields();
    	for(FieldDeclaration f : classFields) if(f.isPublic()) smell.add(n);
    }
    
    @Override
    public void addComment(Node n) {}
}
