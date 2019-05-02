package smell.abuser;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;

import smell.Smell;

public class DataHiding extends Smell {
	public DataHiding() {
		smell = new ArrayList<>();
	}
	
	/**
	 * Method to check if class has encapsulation issues e.g. allowing
	 * Clients to access its internals (attributes should be private)
	 */
    public void visit(ClassOrInterfaceDeclaration n, Void arg) {
    	List<FieldDeclaration> classFields = n.getFields();
    	for(FieldDeclaration f : classFields) {
    		// Public static variables are acceptable as they represent useful constants belonging to class (usually)
    		if(f.isPublic() && !f.isStatic()) {
    			smell.add(n);
    			break;
    		}
    	}
    }
    
    @Override
    public void addComment(Node n) {}
}
