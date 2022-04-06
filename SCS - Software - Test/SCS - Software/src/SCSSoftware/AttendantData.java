package SCSSoftware;

import java.util.*;
import java.util.stream.Collectors;

public class AttendantData {
    Map<String, User> userMap = Database.load();

    Scanner keyboard = new Scanner(System.in);
    String username, password;

    System.out.println("Enter username"); //Comes from UI
    username = keyboard.nextLine();

    if (!userMap.containsKey(username))
    {
        System.out.println("Incorrect user name");
        System.exit(0);
    }   

    System.out.println("Enter password");
    password = keyboard.nextLine();

    User un = userMap.get(username);

        if((un.password).equals(password))
        {
            System.out.println("Correct password");
        }
        else
            System.out.println("Incorrect password");
}
}