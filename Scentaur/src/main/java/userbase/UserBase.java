package userbase;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that holds the user base information
 */
public class UserBase {
	private static Map<String, UserInfo> users = new HashMap<String, UserInfo>();
	
	// return a hashmap containing all the users
	public static Map<String, UserInfo> getUsers(){
		return users;
	}
	
	// return a single user in the hashmap
	public static UserInfo getUser(String sessionID) {
		return users.get(sessionID);
	}

	public static void addUser(String sessionID, UserInfo user) {
		users.put(sessionID, user);
	}
	
	public static void removeUser(String sessionID) {
		users.remove(sessionID);
	}

}
