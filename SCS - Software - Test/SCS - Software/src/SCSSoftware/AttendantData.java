package SCSSoftware;

import java.util.*;
import java.util.stream.Collectors;

public class AttendantData {
    public Map<String, String> userMap = new HashMap<String, String>();

    Scanner keyboard = new Scanner(System.in);
    private String username;

    public void addAttendant(String username, String password) {
        userMap.put(username, password);
    }

    public boolean removeAttendant(String username) {
        if (userMap.containsKey(username)) {
                userMap.remove(username);
                return true;
        }
        return false;
    }

    public boolean logIn(String username, String password){
        if (!userMap.containsKey(username)) return false;
        if (!userMap.get(username).equals(password)) return false;
        if(this.username != null) return false;

        this.username = username;
        return true;

    }

    public void logOut(){
        this.username = null;
    }

    public String getCurrentUser(){
        return username;
    }

    public Map getUsers(){
        return userMap;
    }


}