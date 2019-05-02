package smell.coupler;

import java.util.ArrayList;
import java.util.Optional;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;

import smell.Smell;

/**
 * Detect message chain smell where chains are greater than 2
 * A method chain is where a method call is chained up to many method calls.
 * 
 */

public class MessageChain extends Smell{
	
	public MessageChain() {
		smell = new ArrayList<>();
	}

	public void visit(MethodCallExpr n, Void args) {
		Optional<Expression> subExpression = n.getScope();	//Generate its subexpression

		if(subExpression.isPresent()) {
			if(subExpression.get().isMethodCallExpr()) { //If this next expression isMethodCallExpression...
				
				MethodCallExpr ssExpr = subExpression.get().asMethodCallExpr();	
				
				if(ssExpr.getScope().get().isMethodCallExpr()) {	//And if the sub isMethodCallExpression...
					smell.add(n);	//A message chain smell
				}
			}
		}
	}
	
	@Override
	public void addComment(Node n) {
		// TODO Auto-generated method stub
		
	}
	
	
}
