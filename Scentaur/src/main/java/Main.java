import java.io.File;
import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import smell.bloater.LongMethod;
import smell.bloater.LongParameterList;

public class Main {

	public static void main(String[] args) throws IOException {
		Parser parse = new Parser(new File(System.getProperty("user.dir")).getAbsolutePath());
		List<CompilationUnit> all = parse.getAllCu();

		LongParameterList longParameterList = new LongParameterList();
		LongMethod longMethod = new LongMethod();
		
		//Test longParameterList
		all.forEach(c -> {
			c.accept(longParameterList, null);
			c.accept(longMethod, null);
		});
		
		System.out.println(longMethod.getClass());
		for(Node node: longMethod.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}
		
		System.out.println(longParameterList.getClass());
		for(Node node: longParameterList.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}
		
		//Has edited code
		for(CompilationUnit c: all) {
			System.out.println(c);
		}
		
	}

}
