package fileaddress_base;

import java.util.HashMap;
import java.util.Map;

// Every request from the user maps to the address of the file the user uploads

public class FileMap {
	
	private static Map<String, String> fileMap = new HashMap<String, String>();
	
	public static void addFile(String sessionID, String address) {
		fileMap.put(sessionID, address);
	}
	
	public static void removeFile(String key) {
		fileMap.remove(key);
	}
	
	public static String getFile(String key) {
		return fileMap.get(key);
	}

}
