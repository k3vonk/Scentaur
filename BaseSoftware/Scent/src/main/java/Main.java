import java.io.IOException;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;

import parser.Parser;
import smell.abuser.SwitchStatement;
import smell.coupler.FeatureEnvy;
import smell.coupler.InappropriateIntimacy;
import smell.coupler.MessageChain;
import smell.coupler.MiddleMan;
import smell.dispensables.DeadCode;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Parser parse = new Parser(System.getProperty("user.dir"));

		List<CompilationUnit> all = parse.getAllCu();
		
		MessageChain messageChain = new MessageChain();
		MiddleMan middleMan	= new MiddleMan();
		FeatureEnvy featureEnvy = new FeatureEnvy();
		SwitchStatement switchStm = new SwitchStatement();
		InappropriateIntimacy inapIntimacy = new InappropriateIntimacy();
		
		all.forEach(c -> {
			c.accept(messageChain, null);		
			c.accept(middleMan, null);
			c.accept(featureEnvy, null);
			c.accept(switchStm, null);
			c.accept(inapIntimacy, null);
		});
		

		// Message Chaining
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
		
		
		// Inappropriate Intimacy
		System.out.println("\n___INAPPROPRIATE INTIMACY___\n");
		for(Node node : inapIntimacy.getIssue()) {
			System.out.println(node.getBegin().get().line + " " + node.findCompilationUnit().get().getStorage().get().getFileName());
		}

	}

}
