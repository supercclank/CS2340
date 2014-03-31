package com.example.financialplanner;
import java.util.HashMap;
/**
 * Class responsible for keeping a record of User and Admin objects.
 * @author Cory Brzycki
 * @version 1.0
 */
public class Register {
    /**
     * The records of userNames stored in this register.
     */
    private HashMap<String, User> userNames; 
    /**
     * The admin of this register.
     */
    private Admin admin;
    /**
     * The user currently being edited/used by this register.
     */
    private User currentUser;
    /**
     * creates register object, initializes the HashMap and creates a default admin.
     */
    public Register() {
        userNames = new HashMap<String, User>();
        admin = new Admin("admin_", "admin1");
    }
    /**
     * Attempts to make a new user and adds it to the HashMap.
     * @param userName - userName of the new User to be made
     * @param password - the password of the new User to be made
     * @return true if user was successfully added to the HashMap
     *                 false otherwise
     */
    public boolean addUser(String userName, String password) {
        if (userNames.get(userName) == null) {
            userNames.put(userName, new User(userName, password));
            return true;
        }

        return false;

    }
    /**
     * getter for HashMap of Users.
     * @return HashMap<String, User> userNames - the HashMap of users
     */
    public HashMap<String, User> getUsers() {
        return userNames;

    }
    /**
     * updates a user if it is contained in the userNames HashMap.
     * @param user - the user to update
     * @return true if user was found and updated
     *                 false otherwise
     * @postCondition - if user found, the record of user has been updated to reflect desired changes
     */
    public boolean updateUserInformation(User user) {
        if (userNames.get(user.getUserName()) == null) {
            return false;

        }

        userNames.put(user.getUserName(), user);
        return true;

    }
    /**
     * sets the currenUser to be null.
     */
    public void resetUser() {
        currentUser = null;
    }
    /**
     * Metod that returns the currentUser (the user in focus).
     * @return User - the pointer for currentUser in userNames
     */
    protected User getUser() {
        return userNames.get(currentUser.getUserName());
    }
    /**
     * Checks to see if passed information lines up with a user.
     * @param username - the userName to search for
     * @param password - the password of the desired User
     * @return true if a user exists that matches provided information
     *                 false otherwise
     */
    public boolean checkInformation(String username, String password) {
        User user = userNames.get(username);
        if (user != null && user.checkPass(password)) {
            currentUser = user;
            System.out.println("the current user is : " + user);
            return true;
        }
        //return (user == null) ? false : user.checkPass(password);
        else {
            if (admin.getUserName().equals(username) && admin.checkPass(password)) {
                return true;
            }
        }
        return false;
    }
}
