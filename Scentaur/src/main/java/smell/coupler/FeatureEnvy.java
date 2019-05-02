package smell.coupler;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;


import smell.Smell;

/**
 * A class that detects feature envy of a class. This is done by detecting if methods in a class
 * are using public methods that are not declared in its own class.
 * 
 */

public class FeatureEnvy extends Smell{

	public static final int FEATURE_ENVY_COUNT = 10;
	
	public FeatureEnvy() {
		smell = new ArrayList<>();
	}
	
	public void visit(MethodDeclaration n, Void args) {
	
		int counter = 0; 																			//Number of methods calls that aren't part of the class
		List<MethodCallExpr> mce = n.findAll(MethodCallExpr.class); 								//Find all method call expressions of a class
		List<MethodDeclaration> methods = n.getParentNode().get().findAll(MethodDeclaration.class);	//All existing methods of a class
		
		if(!mce.isEmpty()) {	//If there are method call expressions..
			for(MethodCallExpr m: mce) { 
				boolean isEqual = true;
				
				for(MethodDeclaration p: methods) {
					if(!m.getName().equals(p.getName())) {	//If there are method call expressions that matches with method declaration names...
						isEqual = false;
					}
				}
				
				if(!isEqual) { counter++; }		//Counter incrementer for each method calls that aren't part of the class
			}

		}
		
		//Feature Envy detected
		if(counter > FEATURE_ENVY_COUNT) 
			smell.add(n);
		
	}

	@Override
	public void addComment(Node n) {
		// TODO Auto-generated method stub
		
	}
	
	
}
