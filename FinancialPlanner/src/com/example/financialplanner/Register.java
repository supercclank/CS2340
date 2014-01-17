package com.example.financialplanner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.os.Parcel;
import android.os.Parcelable;

public class Register implements JsonParser {
	private HashMap<String, User> userNames; 
	private Admin admin;

	//creates register object, initializes the HashMap and creates a default admin
	public Register() {
		userNames = new HashMap<String, User>();
		admin = new Admin("admin", "admin1");

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

	//checks to see if info enters matches a user
	public boolean checkInformation(String username, String password) {
		User user = userNames.get(username);
		return (user==null) ? false : user.checkPass(password);

	}

	//not needed or used, just required by Parcelable interface
//	@Override
//	public int describeContents() {
//		return 0;
//	}
//	//figure out the best way to flatten the map correctly
//	@Override
//	public void writeToParcel(Parcel out, int flags) {
//		//out.writem
//	}
//	 public static final Parcelable.Creator<Register> CREATOR
//			     = new Parcelable.Creator<Register>() {
//			 public Register createFromParcel(Parcel in) {
//			     return new Register(in);
//			 }
//			
//			 public Register[] newArray(int size) {
//			     return new Register[size];
//			 }
//			};

	@Override
	public Object toObject(String json) {
		ArrayList<User> users = new ArrayList<User>();
		//implement the string logic to figure out how many users/loop params
		return new Register();

	}

	@Override
	public String toJson(Object t) {
		// TODO Auto-generated method stub
		return null;

	}

}
