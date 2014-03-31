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
    /**
     * the accountsthat belong to this User.
     */
    private ArrayList<Account> accounts;
    /**
     * the username of this User.
     */
    private String userName;
    /**
     * the password of this User.
     */
    private String password;

    /**
     * Constructor for User.
     * @param userNameP - the userName of a to be created User
     * @param passwordP - the password of a to be created User
     */
    public User(String userNameP, String passwordP) {
        this.accounts = new ArrayList<Account>();
        this.userName = userNameP;
        this.password = passwordP;

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
     * @param passwordP - the password to be checked
     * @return boolean - true if the passed string matches this User's password
     *                         false otherwise
     */
    public boolean checkPass(String passwordP) {
        return this.password.equals(passwordP);

    }
    /**
     * Adds an Account to this User's profile.
     * @param account - the Account to be added
     * @return boolean - true if the Account was added or was not already present
     *                     false otherwise
     */
    public boolean addAccount(Account account) {
        if (accounts.indexOf(account) < 0) {
            accounts.add(account);
            return true;
        }
        return false;

    }
    /**
     * Deletes an Account from this User's profile.
     * @param account - the Account to be deleted
     * @return boolean - true if the Account was present and deleted
     *                         false otherwise
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
