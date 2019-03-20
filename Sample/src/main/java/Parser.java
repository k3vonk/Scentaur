
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.utils.SourceRoot;

public class Parser {
	
	private String dir;
	private SourceRoot sourceRoot;
	private CombinedTypeSolver typeSolver;
	private ParserConfiguration parserConfiguration;
	private List<ParseResult<CompilationUnit>> parseResults;
	
	public Parser(String dir) throws IOException {
		this.dir = dir;
		
		//Configure the Symbol Solver
		configureSymbolSolver();
		parserConfiguration = new ParserConfiguration().setSymbolResolver(new JavaSymbolSolver(typeSolver));
		
		//Parse all source files
		sourceRoot = new SourceRoot(Paths.get(dir));
		sourceRoot.setParserConfiguration(parserConfiguration);
		parseResults = sourceRoot.tryToParse("");
		
	}
	
	/**
	 * Configure the Symbol Solver
	 */
	private void configureSymbolSolver() {
		this.typeSolver = new CombinedTypeSolver(
				new ReflectionTypeSolver(),
				new JavaParserTypeSolver(new File(dir)));
	}
	
	/**
	 * Gets all compilation units of all java files
	 * @return
	 */
	public List<CompilationUnit> getAllCu(){
		return parseResults.stream()
				.filter(ParseResult::isSuccessful)
				.map(r -> r.getResult().get())
				.collect(Collectors.toList());
	}
	
	public static void main(String[] args) throws IOException {
		Parser parse = new Parser("C:\\Users\\Gajun\\Desktop\\Software Engineering III\\Scentaur\\src\\main");
		List<CompilationUnit> all = parse.getAllCu();
		
		for(CompilationUnit c: all) {
			System.out.println(c);
		}
	}

}
