package smell.abuser;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import smell.Smell;

public class RefusedBequest extends Smell{

	public RefusedBequest() {
		smell = new ArrayList<>();
	}
	
	public void visit(ClassOrInterfaceDeclaration n, Void args) {
		
		System.out.println(n.getExtendedTypes());
	}
	
	@Override
	public void addComment(Node n) {
		// TODO Auto-generated method stub
		
	}
	
}
