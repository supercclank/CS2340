package com.example.financialplanner;
/**
 * Class for an Admin that is responsible for resetting the passwords of User.
 * @author Cory Brzycki
 * @version 1.0
 */
public class Admin {
    /**
     * The username of this Admin.
     */
    private String username;
    /**
     * The password of this Admin.
     */
    private String password;
    /**
     * constructor for Admin.
     * @param usernameP - the userName of this Admin
     * @param passwordP - the password of this Admin
     */
    public Admin(String usernameP, String passwordP) {
        this.username = usernameP;
        this.password = passwordP;
    }
    /**
     * checks to see if the password is correct.
     * @param passwordP - the password to check
     * @return true if this.password is equal to password
     *                 false otherwise
     */
    public boolean checkPass(String passwordP) {
        if (this.password.equals(passwordP)) {
            return true;
        }
        return false;
    }
    /**
     * getter for user name.
     * @return the username of this admin
     */
    public String getUserName() {
        return this.username;
    }
    /**
     * method that resets a users password by returning a new User with the same username and new password.
     * @param user - the new user that was created that relfects a password change
     * @param passwordP - the password to be used in creation of the new User
     * @return User newUser - the new User object that reflects a change in password
     */
    //returns a new user using the old userName with a new password, to over-write user in storage hashmap
    public User  resetPassword(User user, String passwordP) {
        User newUser = new User(user.getUserName(), passwordP);
        return newUser;
    }
}
