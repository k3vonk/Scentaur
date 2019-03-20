
import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class DirExplorer{
	
	private String root;
	
	public DirExplorer(String root) {
		this.root = root;
	}
	
	/**
	 * Using FileUtils to obtain all the java files within the root directory and its sub directories
	 * @return A list of files which are java files specifically
	 */
	public List<File> fileLookup(){
		return (List<File>) FileUtils.listFiles(new File(root), new String[] {"java"}, true);	
	}
	
	/**
	 * @return root directory string
	 */
	public String getRoot() {
		return root;
	}
	
		
}
