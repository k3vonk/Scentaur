package smell.coupler;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.type.Type;

import smell.Smell;

/**
 * A class that detects the smell MiddleMan
 * Middleman is when a class performs only one action, delegating work to another class.
 *
 */
public class MiddleMan extends Smell{

	public MiddleMan() {
		smell = new ArrayList<>();
	}
	
	public void visit(ClassOrInterfaceDeclaration n, Void args) {
		
		List<VariableDeclarator> classVars = new ArrayList<>();
		
		//Check if this class uses fields from another class
		for(FieldDeclaration fd: n.getFields()) {
			Type fType = fd.getElementType();
			
			if(fType.isClassOrInterfaceType()) { //If this field is a class or interface type...
				classVars.addAll(fd.getVariables());
			}
		}
		
		int totalMethodCalls = n.findAll(MethodCallExpr.class).size();	//Amount of Method calls in this class
		int callOther = 0;
		
		//counting delegation of work
		for(VariableDeclarator vd: classVars) {
			callOther += (int) n.findAll(ReturnStmt.class).stream()
					.filter(s -> s.findAll(MethodCallExpr.class).size() > 0)
					.filter(s -> s.toString().contains(vd.toString())).count();
		}
		
		//Calculation for Middle Man
		if((callOther*1.0)/totalMethodCalls > 0.8 && classVars.size() < 2) {
			smell.add(n);
		}
		
		
		super.visit(n, null); //Visits inner classes
		
		
	}
	
	@Override
	public void addComment(Node n) {
		// TODO Auto-generated method stub
		
	}

}
