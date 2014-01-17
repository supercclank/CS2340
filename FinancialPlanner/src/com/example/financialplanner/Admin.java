package com.example.financialplanner;

import java.util.HashMap;

public class Admin {
	private String username;
	private String password;

	//constructor for Admin
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;

	}

	//checks to see if passwords match (for log in, need to rewrite, plain text = bad)
	public boolean checkPass(String password) {
		if(this.password.equals(password)) {
			return true;

		}

		return false;

	}

	//returns a new user using the old userName with a new password, to over-write user in storage hashmap
	public User resetPassword(User user, String password) {
		User newUser = new User(user.getUserName(),password);
		return newUser;

	}

}
