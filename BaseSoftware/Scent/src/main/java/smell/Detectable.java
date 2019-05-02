package smell;

import java.util.List;
import java.util.Map;

import com.github.javaparser.ast.CompilationUnit;

/**
 * Plug and play for Bloater, Coupler, Abuser and Dispensable
 *
 */
public interface Detectable<T> {
	public void detect(List<CompilationUnit> cu);
	public Map<T, Smell> getMapUsingFileName(String fileName);
	public List<String> getFileNames();
}
