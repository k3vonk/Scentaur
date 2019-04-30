import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import parser.Parser;
import smell.abuser.RefusedBequest;
import smell.abuser.SwitchStatement;
import smell.coupler.FeatureEnvy;
import smell.coupler.MessageChain;
import smell.coupler.MiddleMan;

public class Main {

	public static void main(String[] args) throws IOException {
		Parser parse = new Parser("C:\\Users\\Gajun\\Desktop\\Software Engineering III\\testProject\\testProject");
		List<CompilationUnit> all = parse.getAllCu();
		
		MessageChain messageChain = new MessageChain();
		MiddleMan middleMan	= new MiddleMan();
		FeatureEnvy featureEnvy = new FeatureEnvy();
		
		SwitchStatement switchStm = new SwitchStatement();
		RefusedBequest refusedBequest = new RefusedBequest();
		
		all.forEach(c -> {
			c.accept(messageChain, null);		
			c.accept(middleMan, null);
			c.accept(featureEnvy, null);
			c.accept(switchStm, null);
			c.accept(refusedBequest, null);
		});
		
		/*for(Node node: switchStm.getIssue()) {
			System.out.println(node.getBegin().get().line + ": " + node);
		}*/
	}

}
