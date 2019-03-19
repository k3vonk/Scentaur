import java.io.File;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		DirExplorer dir = new DirExplorer(System.getProperty("user.dir"));
        List<File> files = dir.fileLookup();
        files.forEach(file -> {
            Parser myParser = new Parser(file.getPath());
            System.out.println(myParser.toString());
        });

	}

}
