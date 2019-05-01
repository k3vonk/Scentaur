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
	
	public Report(Abuser abuser, Bloater bloater, Coupler coupler, Dispensable dispensable) {
		this.abuser = abuser;
		this.bloater = bloater;
		this.coupler = coupler;
		this.dispensable = dispensable;
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
