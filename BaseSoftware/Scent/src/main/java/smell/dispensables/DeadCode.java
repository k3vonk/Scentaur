package smell.dispensables;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.NameExpr;

import smell.Smell;

/**
 * Dead Code smell detector that detects if variables, parameters and fields are dead code.
 *
 */

public class DeadCode extends Smell{

	public DeadCode() {
		smell = new ArrayList<>();
	}
	
	/**
	 * Checks Dead Code within a method
	 */
	public void visit(MethodDeclaration n, Void args) {
		
		List<String> paramName = new ArrayList<>();
		
		for(Parameter param: n.getParameters()) { //Obtain parameters
			if(!param.getName().toString().equals("args")) {	//if it is not a main argument...
				paramName.add(param.getName().toString());
			}
		}
		
		boolean isDead = false;
		if(n.getBody().isPresent() && !paramName.isEmpty()) { //If a method body exists...
			List<NameExpr> names = n.getBody().get().findAll(NameExpr.class); //Find all the name expressions of a method
			
			if(names.isEmpty()) isDead = true;
			
			for(NameExpr name: names) {
				if(!paramName.contains(name.toString())) {
					isDead = true;
				}
			}
		}
		
		if(isDead) smell.add(n);
	}
	
	/**
	 * Visits the whole class and checks if the class fields are dead code
	 */
	public void visit(ClassOrInterfaceDeclaration n, Void args) {
	
		List<String> variableNames = new ArrayList<>();	//Class variable names
		List<String> mdNames = new ArrayList<>();		//Method variables
		
		for(VariableDeclarator vd:n.findAll(VariableDeclarator.class)) { //Get variable declared names
			variableNames.add(vd.getNameAsString());
		}

		List<MethodDeclaration> md = n.findAll(MethodDeclaration.class);
		
		for(MethodDeclaration m: md) { //For each method find their variables
			if(m.getBody().isPresent()) {
				List<NameExpr> nameExpr = m.getBody().get().findAll(NameExpr.class);
				
				for(NameExpr name: nameExpr) { //Fill in mdNames list with method variables
					if(!mdNames.contains(name.toString())) {
						mdNames.add(name.toString());
					}
				}
			}
		}
	
		boolean isDead = false;
		//Check if it is dead code 
		for(String check: variableNames) {
			if(!mdNames.contains(check) && !variableNames.isEmpty()) {
				isDead = true;
			}
		}
		
		if(isDead) smell.add(n);
	}
	
	
	@Override
	public void addComment(Node n) {
		// TODO Auto-generated method stub
		
	}

}
