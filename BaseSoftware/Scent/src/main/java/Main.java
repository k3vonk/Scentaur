import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import parser.Parser;
import smell.abuser.SwitchStatement;
import smell.coupler.FeatureEnvy;
import smell.coupler.MessageChain;
import smell.coupler.MiddleMan;
import smell.dispensable.DeadCode;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Parser parse = new Parser("C:\\Users\\Gajun\\Desktop\\Software Engineering III\\testProject\\src");

		List<CompilationUnit> all = parse.getAllCu();
		
		MessageChain messageChain = new MessageChain();
		MiddleMan middleMan	= new MiddleMan();
		FeatureEnvy featureEnvy = new FeatureEnvy();
		
		SwitchStatement switchStm = new SwitchStatement();
		
		DeadCode deadCode = new DeadCode();
		
		all.forEach(c -> {
			c.accept(messageChain, null);		
			c.accept(middleMan, null);
			c.accept(featureEnvy, null);
			c.accept(switchStm, null);
			c.accept(deadCode, null);

		});
		
		
	}

}
