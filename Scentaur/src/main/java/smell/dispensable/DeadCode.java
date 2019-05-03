package smell.dispensable;

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
	 * Visits the whole class and checks if the class fields are dead code and the method fields
	 */
	public void visit(ClassOrInterfaceDeclaration n, Void args) {
	
		List<MethodDeclaration> md = n.getMethods();
		List<String> paramName = new ArrayList<>();
		
		//This code was used to detect dead variables but it cant
		List<String> variableNames = new ArrayList<>();	//Class variable names
		List<String> mdNames = new ArrayList<>();		//Method variables
		
		for(VariableDeclarator vd:n.findAll(VariableDeclarator.class)) { //Get variable declared names
			variableNames.add(vd.getNameAsString());
		}
		
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
	
		if(!mdNames.isEmpty()) {
			//Check if it is dead code 
			for(String check: variableNames) {
				if(!mdNames.contains(check) && !variableNames.isEmpty()) {
					if(n.getFieldByName(check).isPresent()) {
						smell.add(n.getFieldByName(check).get());
					}
				}
			}
		}
			
		//Search the methods of the class
		for(MethodDeclaration method: md) {
			for(Parameter param: method.getParameters()) { //Obtain parameters
				if(!param.getName().toString().equals("args") && !method.getParameters().isEmpty()) {	//if it is not a main argument...
					paramName.add(param.getName().toString());
				}
			}
			
			if(method.getBody().isPresent() && !paramName.isEmpty()) { //If a method body exists...
				List<NameExpr> names = method.getBody().get().findAll(NameExpr.class); //Find all the name expressions of a method
				List<String> nameString = new ArrayList<>();
				
				for(NameExpr name: names) {
					nameString.add(name.toString());
				}
				
				for(String param: paramName) {
					if(!nameString.contains(param)) {
						smell.add(method);
						break;
					}
				}
			}
			
		}
	}
	
	
	@Override
	public void addComment(Node n) {
		// TODO Auto-generated method stub
		
	}

}
