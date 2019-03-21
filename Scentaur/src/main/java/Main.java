import java.io.File;
import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import smell.bloater.LongMethodDetector;
import smell.bloater.LongParameterList;

public class Main {

	public static void main(String[] args) throws IOException {
		Parser parse = new Parser(new File("..\\testProject\\src").getAbsolutePath());
		List<CompilationUnit> all = parse.getAllCu();

		LongParameterList longParameterList = new LongParameterList();
		LongMethodDetector longMethod = new LongMethodDetector();
		
		//Test Long Method
		all.forEach(c -> {
			
			//System.out.println(c.getStorage().get().getPath());	//Test obtains all java file path
			c.accept(longParameterList, null);
			c.accept(longMethod, null);
		});
		
		//Parameter Method
		for(MethodDeclaration md: longParameterList.get()) {
			System.out.println(md);
		}
	
		//LONG METHOD
		for(MethodDeclaration md: longMethod.get()) {
			System.out.println(md);
		}
	
		
	}

}