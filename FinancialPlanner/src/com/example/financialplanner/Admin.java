package com.example.financialplanner;
/**
 * Class for an Admin that is responsible for resetting the passwords of Users
 * @author Cory Brzycki
 * @version 1.0
 */
public class Admin {
	private String username;
	private String password;
	/**
	 * constructor for Admin
	 * @param String username - the userName of this Admin
	 * @param String password - the password of this Admin
	 */
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	/**
	 * checks to see if the password is correct
	 * @param String password - the password to check
	 * @return true if this.password is equal to password
	 * 				false otherwise
	 */
	public boolean checkPass(String password) {
		if(this.password.equals(password)) {
			return true;
		}
		return false;
	}
	/**
	 * method that resets a users password by returning a new User with the same username and new password
	 * @param User user - the new user that was created that relfects a password change
	 * @param String password - the password to be used in creation of the new User
	 * @return User newUser - the new User object that reflects a change in password
	 */
	//returns a new user using the old userName with a new password, to over-write user in storage hashmap
	public User  resetPassword(User user, String password) {
		User newUser = new User(user.getUserName(),password);
		return newUser;
	}
}
