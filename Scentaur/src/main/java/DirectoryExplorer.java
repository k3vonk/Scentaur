

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.*;

import org.apache.commons.io.FileUtils;


class DirectoryExplorer {


    public static void main(String arg[]) {
        DirectoryExplorer myDirectoryExplorer = new DirectoryExplorer(System.getProperty("user.dir"));
        List<File> files = myDirectoryExplorer.lookupFiles();
        files.forEach(file -> {
            System.out.println("=============" + file.getName() + "=============");
            Parser myParser = new Parser(file.getPath());
            myParser.parse();
        });

    }


    String directory;

    public DirectoryExplorer(String directory) {
        this.directory = directory;
    }

    public List<File> lookupFiles() {

        List<File> files = (List<File>) FileUtils.listFiles(new File(directory), new String[] { "java"}, true);
        System.out.println(files.size());
        return files;
    }


}