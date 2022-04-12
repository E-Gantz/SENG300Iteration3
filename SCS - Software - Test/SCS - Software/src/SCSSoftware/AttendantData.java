package SCSSoftware;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is responsible to adding new attendants into the hashmap and
 * validates which attendants are able to able to log into the attendant system
 */
public class AttendantData {
	public Map<String, String> userMap = new HashMap<String, String>();

	Scanner keyboard = new Scanner(System.in);
	private String username;

	/**
	 * This method adds a new attendans username and password into the hashmap
	 * 
	 * @param username
	 * @param password
	 * 
	 */
	public void addAttendant(String username, String password) {
		userMap.put(username, password);
	}

	/**
	 * This method removes an attendant from the hashmap given the username as a
	 * parameter
	 * 
	 * @param username
	 * 
	 */
	public boolean removeAttendant(String username) {
		if (userMap.containsKey(username)) {
			userMap.remove(username);
			return true;
		}
		return false;
	}

	/**
	 * This method allows an attendant to log into the selfcheckoutsystem using
	 * their username & password as parameters passed in and then returns a true or
	 * false response
	 * 
	 * @param username
	 * @param password
	 * @return true
	 */
	public boolean logIn(String username, String password) {
		if (!userMap.containsKey(username))
			return false;
		if (!userMap.get(username).equals(password))
			return false;
		if (this.username != null)
			return false;

		this.username = username;
		return true;
	}

	/**
	 * This method allows an attendant to log into the selfcheckoutsystem using
	 * their username & password as parameters passed in and then returns a true or
	 * false response
	 * 
	 * @param username
	 * @param password
	 * @return true
	 */
	public void logOut() {
		this.username = null;
	}

	/**
	 * This getter method returns which attendant is currently using the system
	 * 
	 * @return username
	 */
	public String getCurrentUser() {
		return username;
	}

	/**
	 * This getter method returns all attendant stored in the checkout system
	 * 
	 * @return userMap
	 */
	public Map<String, String> getUsers() {
		return userMap;
	}

}