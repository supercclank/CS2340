package com.example.financialplanner;

import java.util.ArrayList;
//Class that defines a user, keeps track of their accounts, username and password
///TODO - implement encryption of some type for password Storing (plain text = bad)
public class User {
    private ArrayList<Account> accounts;
    private String userName;
    private String password;
    public User(String userName, String password){
        accounts = new ArrayList<Account>();
        this.userName = userName;
        this.password = password;
    }
    //returns all accounts that belong to this user
    public ArrayList<Account> getAccounts(){
        return accounts;
    }
    //checks to see if passwords match (for log in, need to rewrite, plain text = bad)
    public boolean checkPass(String password){
        return this.password.equals(password));
    }
    //adds account to this users history
    public boolean addAccount(Account account){
        return accounts.add(account);
    }
    //deletes account from this users history
    public boolean deleteAccount(Account account){
        return (accounts.remove(account));
    }
    //
    public String getUserName(){
        return userName;
    }
}
