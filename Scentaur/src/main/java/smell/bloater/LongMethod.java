package smell.bloater;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;


public class LongMethod extends Bloater{
	
	public static final int METHOD_LENGTH_THRESHOLD = 40;
	private List<MethodDeclaration> longMethods;
	
	public LongMethod() {
		longMethods = new ArrayList<>();
	}
	
	public void visit(MethodDeclaration n, Void args) {
		String[] lines = n.getBody().toString().split("\r\n|\r|\n");
		if(lines.length >= METHOD_LENGTH_THRESHOLD) longMethods.add(n);
	}

	@Override
	public void addComment(Node n) {}	
}
