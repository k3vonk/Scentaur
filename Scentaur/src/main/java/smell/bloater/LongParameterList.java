package smell.bloater;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class LongParameterList extends VoidVisitorAdapter<Void> implements Bloatable {

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
        }
        
        System.out.println(n.getParentNode().get().findCompilationUnit().get().getStorage().get().getPath());
    }

	public boolean isClassEmpty(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setThreshold(int value) {
		// TODO Auto-generated method stub
		
	}
    
}