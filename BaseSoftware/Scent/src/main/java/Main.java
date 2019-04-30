import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import parser.Parser;
import smell.coupler.FeatureEnvy;
import smell.coupler.MessageChain;
import smell.coupler.MiddleMan;

public class Main {

	public static void main(String[] args) throws IOException {
		Parser parse = new Parser("C:\\Users\\Gajun\\Desktop\\BaseSoftware\\Scent\\src\\main\\java\\smell");
		List<CompilationUnit> all = parse.getAllCu();
		
		MessageChain messageChain = new MessageChain();
		MiddleMan middleMan	= new MiddleMan();
		FeatureEnvy featureEnvy = new FeatureEnvy();
		
		all.forEach(c -> {
			c.accept(messageChain, null);		
			c.accept(middleMan, null);
			c.accept(featureEnvy, null);
		});
		
		for(Node node: messageChain.getIssue()) {
			System.out.println(node.getBegin().get().line + ": " + node);
		}
	}

}
