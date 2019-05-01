import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import parser.Parser;
import smell.abuser.SwitchStatement;
import smell.coupler.FeatureEnvy;
import smell.coupler.InappropriateIntimacy;
import smell.coupler.MessageChain;
import smell.coupler.MiddleMan;
import smell.dispensable.DeadCode;

public class Main {

	public static void main(String[] args) throws IOException {
<<<<<<< HEAD
		Parser parse = new Parser("C:\\Users\\Gajun\\Desktop\\Software Engineering III\\testProject\\src");
=======
		Parser parse = new Parser(System.getProperty("user.dir"));
>>>>>>> 7e49039c604c16dd4186dfb09ecf4c30903907d8
		List<CompilationUnit> all = parse.getAllCu();
		
		MessageChain messageChain = new MessageChain();
		MiddleMan middleMan	= new MiddleMan();
		FeatureEnvy featureEnvy = new FeatureEnvy();
		InappropriateIntimacy inappropriateIntimacy = new InappropriateIntimacy();
		
		SwitchStatement switchStm = new SwitchStatement();
		
		DeadCode deadCode = new DeadCode();
		
		all.forEach(c -> {
			//c.accept(messageChain, null);		
			c.accept(middleMan, null);
			c.accept(featureEnvy, null);
<<<<<<< HEAD
			c.accept(switchStm, null);
			c.accept(deadCode, null);

		});
		
		/*for(Node node: switchStm.getIssue()) {
			System.out.println(node.getBegin().get().line + ": " + node);
		}*/
=======
			c.accept(inappropriateIntimacy, null);
		});
		
		/*for(Node node: messageChain.getIssue()) {
			System.out.println(node.getBegin().get().line + ": " + node);
		}*/
		
		for(Node node: inappropriateIntimacy.getIssue()) {
			System.out.println(node.getBegin().get().line + ": " + node.findCompilationUnit().get().getStorage().get().getFileName());
		}
>>>>>>> 7e49039c604c16dd4186dfb09ecf4c30903907d8
	}

}
