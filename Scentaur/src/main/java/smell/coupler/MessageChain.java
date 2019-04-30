package smell.coupler;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.MethodCallExpr;

import smell.Smell;

public class MessageChain extends Smell{

	public MessageChain() {
		smell = new ArrayList<>();
	}
	
	public void visit(MethodCallExpr n, Void args) {
		
	}
	
	@Override
	public void addComment(Node n) {
		// TODO Auto-generated method stub
		
	}

}
