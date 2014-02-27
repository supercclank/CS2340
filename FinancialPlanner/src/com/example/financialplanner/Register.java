package com.example.financialplanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import android.os.Parcel;
import android.os.Parcelable;

public class Register {
	private HashMap<String, User> userNames; 
	private Admin admin;
	private User currentUser;
	//creates register object, initializes the HashMap and creates a default admin
	public Register() {
		userNames = new HashMap<String, User>();
		admin = new Admin("admin_", "admin1");
	}

//	private Register(Parcel in){
//        this.userNames = null;
//    }

	private Register(ArrayList<User> users) {
		userNames = new HashMap<String, User>();
		for (int x=0; x < users.size(); x++) {
			User u = users.get(x);
			userNames.put(u.getUserName(), u);
		}

	}

	//makes a new user and adds it to the HashMap
	public boolean addUser(String userName, String password) {
		if (userNames.get(userName) == null) {
			userNames.put(userName,new User(userName, password));
			return true;

		}

		return false;

	}

	//gets HashMap of users
	public HashMap<String, User> getUsers() {
		return userNames;

	}

	//updates a user if it is contained in the userNames HashMap
	public boolean updateUserInformation(User user) {
		if(userNames.get(user.getUserName()) == null) {
			return false;

		}

		userNames.put(user.getUserName(), user);
		return true;

	}
	
	public void resetUser(){
		currentUser = null;
	}

	protected User getUser(){
		return userNames.get(currentUser.getUserName());
	}
	//checks to see if info enters matches a user
	public boolean checkInformation(String username, String password) {
		User user = userNames.get(username);
		if (user !=null && user.checkPass(password)){
			currentUser = user;
			System.out.println("the current user is : "+user);
		}
		return (user==null) ? false : user.checkPass(password);

	}
	
	public String toString() {
		String s = "==Begin Register==";
		for(Entry<String, User> entry : userNames.entrySet()) {
		    String key = entry.getKey();
		    User value = entry.getValue();

		    s = s + "\n" + key + "" + value;
		}
		s = s + "\n==End Register==";
		return s;
	}

}
