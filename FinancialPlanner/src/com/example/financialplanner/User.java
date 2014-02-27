package com.example.financialplanner;

import java.util.ArrayList;

/**
 * This class defines a User. Users have usernames and passwords, as well as
 * Accounts which keep track of Transactions.
 *
 * @author Cory
 */
public class User {

	//TODO - implement encryption of some type for password Storing (plain text = bad)

	private ArrayList<Account> accounts;
    private String userName;
    private String password;

    /**
     * Constructs a new User object.
     *
     * @param	userName	- this User's username
     * @param	password	- this User's password
     */
    public User(String userName, String password) {
        this.accounts = new ArrayList<Account>();
        this.userName = userName;
        this.password = password;

    }

    /**
     * Returns a List of all Accounts belonging to this User.
     *
     * @return	the List of all this User's Accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;

    }

    /**
     * Returns whether or not the passed string matches this User's password.
     *
     * @param	password	- the password to be checked
     * @return	true if the passed string is the correct password
     */
    public boolean checkPass(String password) {
        return this.password.equals(password);

    }

    /**
     * Adds an Account to this User's profile.
     *
     * @param	account	- the Account to be added
     * @return	true if the Account was added / was not already present
     */
    public boolean addAccount(Account account) {
    	System.out.println(account);
    	accounts.add(account);
    	System.out.println(accounts);
    	return false;

    }

    /**
     * Deletes an Account from this User's profile.
     *
     * @param	account	- the Account to be deleted
     * @return	true if the Account was present and deleted
     */
    public boolean deleteAccount(Account account) {
        return accounts.remove(account);

    }

    /**
     * Returns this User's username.
     *
     * @return	this User's username
     */
    public String getUserName() {
        return userName;

    }
    
    public String toString() {
    	return userName + " w/ accs: " + accounts;
    }
}
