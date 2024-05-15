//User.java
/*
Purpose:
it allows the user..
to log in
and create an account


it inherits the FileHandler functions, but I call it with "FileHandler." to be more specific

 */
public class User extends FileHandler {

    // Static fields to hold username and password
    static String username;
    static String password;

    // Constructor
    public User(String username, String password) {
        User.username = username;
        User.password = password;
    }

    //to log in user by checking username and password against credentials
    public static boolean login(String username, String password) {
        // Read usernames and passwords from file
        String[] data1 = FileHandler.ReadCol(0, "usersCred.csv", ",");
        String[] data2 = FileHandler.ReadCol(1, "usersCred.csv", ",");

        boolean loggedIn = false;

        // looks if data is read successfully
        if (data1 != null && data2 != null && data1.length == data2.length) {
            for (int j = 0; j < data1.length; j++) {
                if (data1[j].equals(username) && data2[j].equals(password)) {
                    loggedIn = true;
                    break;
                }
            }
        } else {
            System.out.println("Error reading user data.");
        }


        return loggedIn;
    }


    // Method to create a new user account
    public static boolean makeAccount(String username, String password) {
        return FileHandler.createFile("usersCred.csv", username, password, "Username", "Password", "Cannot have same Username");
    }
}

