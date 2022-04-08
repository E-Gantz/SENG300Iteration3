package SCSSoftware;

import java.util.*;
import java.util.stream.Collectors;

public class AttendantData {
    Map<String, String> userMap = new HashMap<String, String>();

    Scanner keyboard = new Scanner(System.in);
    String username, password;

    public void addAttendant(String username, String password) {
        userMap.put(username, password);
    }

    public String removeAttendant(String username, String password) {
        if (userMap.containsKey(username)) {
            String dbPassword = userMap.get(username);
            if (dbPassword.equals(password)) {
                userMap.remove(username);
                return "Attendant removed from the database";
            } else {
                return "Password does not match";
            }
        } else {
            return "Username not found";
        }
    }

    // public boolean validateUsername(String name) {
    //     if (!userMap.containsKey(username)) {
    //         System.out.println("Incorrect user name");
    //         System.exit(0);
    //     }

    //     User un = userMap.get(username);
    // }

    // public boolean validatePassword() {
    //     if ((un.password).equals(password)) {
    //         System.out.println("Correct password");
    //     } else
    //         System.out.println("Incorrect password");
    // }
}