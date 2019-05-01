import java.io.File;
import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import parser.Parser;
import smell.Smell;
import smell.abuser.DataHiding;
import smell.abuser.SwitchStatement;
import smell.bloater.Bloater;
import smell.coupler.FeatureEnvy;
import smell.coupler.MessageChain;
import smell.coupler.MiddleMan;
import smell.dispensable.DeadCode;

/**
 * This is used for testing, which will then moved to test units
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		Parser parse = new Parser(new File("..\\..\\testProject\\src\\testProject").getAbsolutePath());

		List<CompilationUnit> all = parse.getAllCu();
		
		Bloater bloater = new Bloater();
		bloater.detect(all);
		

		//========================Testing single smells at a time===========================
		/*Smell messageChain = new MessageChain();
		Smell middleMan	= new MiddleMan();
		Smell featureEnvy = new FeatureEnvy();
		Smell switchStm = new SwitchStatement();
		Smell dataHiding = new DataHiding();
		Smell deadCode = new DeadCode();

		all.forEach(c -> {
			c.accept(messageChain, null);		
			c.accept(middleMan, null);
			c.accept(featureEnvy, null);
			c.accept(switchStm, null);
			c.accept(dataHiding, null);
			c.accept(deadCode, null);
		});*/
		
		/*// Message Chaining
		System.out.println("\n___MESSAGE CHAINING___\n");
		for(Node node : messageChain.getIssue()) {
			System.out.println(node.getBegin().get().line + " " + node.findCompilationUnit().get().getStorage().get().getFileName());
		}
		
		// Middle Man
		System.out.println("\n___MIDDLE MAN___\n");
		for(Node node : middleMan.getIssue()) {
			System.out.println(node.getBegin().get().line + " " + node.findCompilationUnit().get().getStorage().get().getFileName());
		}
		
		// Feature envy
		System.out.println("\n___FEATURE ENVY___\n");
		for(Node node : featureEnvy.getIssue()) {
			System.out.println(node.getBegin().get().line + " " + node.findCompilationUnit().get().getStorage().get().getFileName());
		}
		
		// Switch statement
		System.out.println("\n___SWITCH STATEMENT___\n");
		for(Node node : switchStm.getIssue()) {
			System.out.println(node.getBegin().get().line + " " + node.findCompilationUnit().get().getStorage().get().getFileName());
		}
		
		// Data Hiding
		System.out.println("\n___DATA HIDING___\n");
		for(Node node : dataHiding.getIssue()) {
			System.out.println(node.getBegin().get().line + " " + node.findCompilationUnit().get().getStorage().get().getFileName());
		}
		
		System.out.println("\n___DEAD CODE___\n");
		for(Node node : deadCode.getIssue()) {
			System.out.println(node.getBegin().get().line + " " + node.findCompilationUnit().get().getStorage().get().getFileName());
		}*/
	}

}
