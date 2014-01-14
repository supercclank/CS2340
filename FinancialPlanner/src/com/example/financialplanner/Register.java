package com.example.financialplanner;

import java.util.HashMap;

public class Register {
	private HashMap<String,User> userNames; 
	private Admin admin;
	//creates register object, initializes the HashMap and creates a default admin
	public Register(){
		userNames = new HashMap<String,User>();
		admin = new Admin("admin","admin1");
	}
	//makes a new user and adds it to the HashMap
	public boolean addUser(String userName, String password){
		if (userNames.get(userName)==null)
			{userNames.put(userName,new User(userName,password));
			return true;
			}
		return false;
	}
	//gets HashMap of users
	public HashMap<String,User> getUsers(){
		return userNames;
	}
	//updates a user if it is contained in the userNames HashMap
	public boolean updateUserInformation(User user){
		if(userNames.get(user.getUserName())==null){
			return false;
		}
		userNames.put(user.getUserName(),user);
		return true;
	}
	//checks to see if info enters matches a user
	public boolean checkInformation(String username, String password){
		User user = userNames.get(username);
		return(user==null)?false:user.checkPass(password);
	}
}
