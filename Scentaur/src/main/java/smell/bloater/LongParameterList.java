package smell.bloater;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class LongParameterList extends BloatersDetectors {

    private List<MethodDeclaration> longParameterMethods;

    public LongParameterList(){
    	setThreshold(5);
        longParameterMethods = new ArrayList<>();
    }

    public List<MethodDeclaration> get(){
        return longParameterMethods;
    }

    @Override
    public void visit(MethodDeclaration n, Void args){
        int numParams = n.getParameters().size();
        if (numParams > super.threshold) {
            longParameterMethods.add(n);
        }
    }
    
}