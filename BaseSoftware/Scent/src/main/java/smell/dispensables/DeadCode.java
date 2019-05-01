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

public class DeadCode extends Smell{

	public DeadCode() {
		smell = new ArrayList<>();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void visit(ClassOrInterfaceDeclaration n, Void args) {
		
		List<MethodDeclaration> md	= n.findAll(MethodDeclaration.class);		//All methods in a class
		List<VariableDeclarator> vd = n.findAll(VariableDeclarator.class);		//All variables in a class
		
		for(MethodDeclaration m: md) {
			List<Parameter> p = m.findAll(Parameter.class); //Parameters of a method
			if(m.getBody().isPresent()) {
				List<NameExpr> names = m.getBody().get().findAll(NameExpr.class);
				if(!names.contains(vd) || !names.contains(p)) {
					
				}
			}
		}
	}
	
	@Override
	public void addComment(Node n) {
		// TODO Auto-generated method stub
		
	}

}
