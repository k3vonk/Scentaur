import java.io.File;

import testProject.Car;
import testProject.Truck;

/**
 * 
 * @author 
 *
 */
public class Main {
	public static void main(String[] args) {
		new Car();
		new Truck();
		

        File directory = new File("C:\\Users\\Gajun\\Desktop\\UCD 3rd Year\\0. Semester 2\\Software Engineering\\1. Assignments\\Assignment1\\src\\nosejob");
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
            if (file.isFile()){
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()){
            }
        }
	}
}
