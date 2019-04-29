import java.io.File;
import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import smell.Smell;
import smell.bloater.LargeClass;
import smell.bloater.LongMethod;
import smell.bloater.LongParameterList;
import smell.bloater.PrimitiveObsession;
import smell.dispensable.Comments;

public class Main {

	public static void main(String[] args) throws IOException {
		Parser parse = new Parser(new File("..//testProject//src//testProject").getAbsolutePath());
		List<CompilationUnit> all = parse.getAllCu();
		longParaList(all);
		longMethod(all);
		largeClass(all);
		PrimitiveObsession(all);
		comments(all);
		//Has edited code
		for(CompilationUnit c: all) {
		//	System.out.println(c);
		}

	}
	
	public static void longParaList(List<CompilationUnit> all) {
		Smell longParameterList = new LongParameterList();
		// Test Long Param List
		all.forEach(c -> {
			c.accept(longParameterList, null);			
		});
		
		System.out.println(longParameterList.getClass());
		for(Node node: longParameterList.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}
	}
	
	public static void longMethod(List<CompilationUnit> all) {
		LongMethod longMethod = new LongMethod();
		all.forEach(c -> {
			c.accept(longMethod, null);			
		});
		
		System.out.println(longMethod.getClass());
		for(Node node: longMethod.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}
	}
	
	public static void largeClass(List<CompilationUnit> all) {
		LargeClass largeClass = new LargeClass();
		
		// Test Large Class
		all.forEach(c -> {
			c.accept(largeClass, null);			
		});

		System.out.println(largeClass.getClass());
		for(Node node : largeClass.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}
	}
	
	public static void PrimitiveObsession(List<CompilationUnit> all) {
		PrimitiveObsession primObsession = new PrimitiveObsession();
		
		// Test PrimitiveObsession
		all.forEach(c -> {
			c.accept(primObsession, null);			
		});

		System.out.println(primObsession.getClass());
		for(Node node : primObsession.getIssue()) {
			System.out.println(node.getBegin().get().line);
		}
	}

    public static void comments(List<CompilationUnit> all) {
        Comments comments = new Comments();
        // Test Long Param List
        all.forEach(c -> {
            c.accept(comments, null);
        });

        System.out.println(comments.getClass());
        for(Node node: comments.getIssue()) {
            System.out.println(node.getBegin().get().line);
        }
    }

}
