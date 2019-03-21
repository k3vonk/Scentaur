package smell.bloater;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.body.MethodDeclaration;

public class LongMethodDetector extends BloatersDetectors{
	
	private List<MethodDeclaration> longMethods;
	
	public LongMethodDetector() {
		setThreshold(40);
		longMethods = new ArrayList<>();
	}
	
	public List<MethodDeclaration> get(){
        return longMethods;
    }
	
	 @Override
	    public void visit(MethodDeclaration n, Void args){
	        int methodLenth = n.getEnd().get().line - n.getBegin().get().line;
	        if (methodLenth > super.threshold) {
	            longMethods.add(n);
	        }
	    }
	
}
