import java.io.File;
import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import smell.bloater.LongParameterList;

public class Main {

	public static void main(String[] args) throws IOException {
		Parser parse = new Parser(new File("C:\\Users\\Gajun\\Desktop\\Software Engineering III\\testProject").getAbsolutePath());
		List<CompilationUnit> all = parse.getAllCu();

		LongParameterList longParameterList = new LongParameterList();
		
		
		//Test longParameterList
		all.forEach(c -> {
			c.accept(longParameterList, null);
		});
		
		System.out.println(longParameterList.getClass());
		for(Node node: longParameterList.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}
		
	}

}
