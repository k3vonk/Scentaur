package smell.abuser;

import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;

import smell.Smell;

/**
 * A class that size of switch and if statements
 * Switch Statements are detected if they exceed size greater than 4
 *
 */
public class SwitchStatement extends Smell{

	public static final int SWITCH_STATEMENT_COUNT = 4;
	
	public SwitchStatement() {
		smell = new ArrayList<>();
	}
	
	/**
	 * Visits all switch statements in a class
	 */
	public void visit(SwitchStmt n, Void arg) {
		if(n.getEntries().size() > SWITCH_STATEMENT_COUNT) {
			smell.add(n);
		}
	}
	
	/**
	 * Visits all if statements within a class 
	 */
	public void visit(IfStmt n, Void arg) {
		
		if(n.findAll(IfStmt.class).size() + 1 > SWITCH_STATEMENT_COUNT) {
			smell.add(n);
		}
	}
	
	
	@Override
	public void addComment(Node n) {
		// TODO Auto-generated method stub
		
	}

}
