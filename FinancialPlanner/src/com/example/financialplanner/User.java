package com.example.financialplanner;
import java.util.ArrayList;
import java.util.List;
/**
 * This class defines a User. Users have usernames and passwords, as well as
 * Accounts which keep track of Transactions.
 *
 * @author Cory Brzycki
 * @version 1.0
 */
public class User {
	private ArrayList<Account> accounts;
    private String userName;
    private String password;

    /**
     * Constructor for User
     * @param String userName - the userName of a to be created User
     * @param Password password	- the password of a to be created User
     */
    public User(String userName, String password) {
        this.accounts = new ArrayList<Account>();
        this.userName = userName;
        this.password = password;

    }
    /**
     * Returns a List of all Accounts belonging to this User.
     * @return List - list of all this User's Accounts
     */
    public List<Account> getAccounts() {
        return accounts;

    }

    /**
     * Returns whether or not the passed string matches this User's password.
     * @param String password - the password to be checked
     * @return boolean - true if the passed string matches this User's password
     * 						false otherwise
     */
    public boolean checkPass(String password) {
        return this.password.equals(password);

    }
    /**
     * Adds an Account to this User's profile.
     * @param Account account - the Account to be added
     * @return boolean - true if the Account was added or was not already present
     * 					false otherwise
     */
    public boolean addAccount(Account account) {
    	if (accounts.indexOf(account)<0){
    		accounts.add(account);
    		return true;
    		}
    	return false;

    }
    /**
     * Deletes an Account from this User's profile.
     * @param Account account - the Account to be deleted
     * @return boolean - true if the Account was present and deleted
     * 						false otherwise
     */
    public boolean deleteAccount(Account account) {
        return accounts.remove(account);
    }
    /**
     * getter for username.
     * @return String username - this user's userName
     */
    public String getUserName() {
        return userName;

    }
}
