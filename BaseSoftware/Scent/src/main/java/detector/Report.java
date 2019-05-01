package detector;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

import smell.abuser.Abuser;
import smell.bloater.Bloater;
import smell.coupler.Coupler;
import smell.dispensable.Dispensable;

public class Report {
	
	private Abuser abuser;
	private Bloater bloater;
	private Coupler coupler;
	private Dispensable dispensable;
	
	public Report() {
		this.abuser = new Abuser();
		this.bloater = new Bloater();
		this.coupler = new Coupler();
		this.dispensable = new Dispensable();
	}
	
	/**
	 * Analyzes the Project for abusers, bloaters, couplers and dispensables
	 * @param cu - the project in compilation unit 
	 */
	public void analyzeProject(List<CompilationUnit> cu) {
		abuser.detect(cu);
		bloater.detect(cu);
		coupler.detect(cu);
		dispensable.detect(cu);
	}
}
