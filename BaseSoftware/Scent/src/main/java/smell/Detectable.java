package smell;

import java.util.List;

import com.github.javaparser.ast.CompilationUnit;

/**
 * Plug and play for Bloater, Coupler, Abuser and Dispensable
 *
 */
public interface Detectable {
	public void detect(List<CompilationUnit> cu);
}
