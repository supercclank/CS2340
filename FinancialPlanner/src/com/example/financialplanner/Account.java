package com.example.financialplanner;

import java.util.ArrayList;

import com.example.financialplanner.Transaction.TransactionType;

/**
 * Class that defines an account, keeps track of information specific to this
 * account. as well as providing a transaction, deposit, and withdrawal history
 * @author Cory Brzycki
 * @version 1.0
 */
public class Account {
    /**
     * the name of this account.
     */
    private String name;
    /**
     * the display name of this account.
     */
    private String displayName;
    /**
     * the balance of this account.
     */
    private double balance;
    /**
     * the interest rate of this account.
     */
    private double interestRate;
    /**
     * the list of transactions belonging to this account.
     */
    private ArrayList<Transaction> transactions;
    /**
     * the list of deposits belonging to this account.
     */
    private ArrayList<Transaction> deposits;
    /**
     * the list of withdrawals belonging to this account.
     */
    private  ArrayList<Transaction> withdrawals;

    /**
     * @param nameP
     *            - name of this account
     * @param displayNameP
     *            - name of this account
     * @param balanceP
     *            - starting balance of this account
     * @param interestRateP
     *            - starting interest rate of this account
     */
    public Account(String nameP, String displayNameP, double balanceP,
            double interestRateP) {
        transactions = new ArrayList<Transaction>();
        this.name = nameP;
        this.displayName = displayNameP;
        this.balance = balanceP;
        this.interestRate = interestRateP;
        transactions = new ArrayList<Transaction>();
        deposits = new ArrayList<Transaction>();
        withdrawals = new ArrayList<Transaction>();

    }

    /**
     * getter for name.
     * @return String name - the name associated with this account
     */
    public String getName() {
        return name;
    }

    /**
     * getter for balance.
     * @return double balance - the balance of this account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * getter for displayName.
     * @return String displayName - the display name of this account
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * runs the transaction and adds it to the history.
     * @param transaction - the transaction to perform
     * @return boolean - true if transaction was run and added to transactions
     *         ArrayList
     */
    public boolean performTransaction(Transaction transaction) {
        run(transaction, 1);
        if (transaction.getType() == TransactionType.DEPOSIT) {
            deposits.add(transaction);
        }
        if (transaction.getType() == TransactionType.WITHDRAW) {
            deposits.add(transaction);
        }
        return transactions.add(transaction);

    }

    /**
     * checks to see if this Account is equal to another object for Accounts
     * equality means the same name or displayName.
     * 
     * @param  o - the object to check equality with
     * @return true - if this equals o false otherwise
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        return (this.name.equals(((Account) o).name) || this.displayName
                .equals(((Account) o).displayName));
    }
    @Override
    public int hashCode() {
        return this.name.hashCode() + this.displayName.hashCode();
    }
    /**
     * adds transaction to correct history (deposit, withdrawal) then performs
     * action.
     * 
     * @param  transaction - the transaction to run
     * @param direction - the 'direction' of the transaction 1 if forward -1
     *        if backwards (undoing)
     * @postCondition - balance is updated to reflect addition of transaction
     */
    private void run(Transaction transaction, int direction) {
        TransactionType type = transaction.getType();

        if (direction == 1) {
            if (type == TransactionType.DEPOSIT) {
                deposits.add(transaction);
                deposit(transaction.getValue());

            }
            if (type == TransactionType.WITHDRAW) {
                withdrawals.add(transaction);
                withdraw(transaction.getValue());
            }
        }

        if (direction == -1) {
            if (type == TransactionType.DEPOSIT) {
                deposits.remove(transaction);
                deposit(transaction.getValue() * (-1));

            }
            if (type == TransactionType.WITHDRAW) {
                withdrawals.remove(transaction);
                withdraw(transaction.getValue() * (-1));

            }

        }

    }

    /**
     * adds deposit to balance.
     * @param deposit - the value to deposit
     * @postCondition balance now equals balance plus deposit
     */
    private void deposit(double deposit) {
        balance += deposit;

    }

    /**
     * deducts withdraw from balance.
     * @param withdraw - the value to withdraw
     * @postCondition balance now equals balance minus withdraw
     */
    private void withdraw(double withdraw) {
        balance -= withdraw;

    }

    /**
     * removes transaction to correct history (deposit, withdrawal) then
     * performs action in reverse.
     * @param transaction - transaction to undo
     * @postCondition balance changes to reflect the undone transaction
     */
    public void undoTransaction(Transaction transaction) {
        run(transaction, -1);
        transactions.remove(transaction);

    }
    /**
     * method that gains interest of this account.
     */
    public void performGainInterest() {

    }

    /**
     * getter for Transactions.
     * @return ArrayList<Transaction> transactions - the list of transactions
     */
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * getter for withdrawals.
     * @return ArrayList<Transaction> withdrawals - the list of withdrawals
     */
    public ArrayList<Transaction> getWithdrawals() {
        return withdrawals;
    }

    /**
     * getter for deposits.
     * @return ArrayList<Transaction> deposits - the list of deposits
     */
    public ArrayList<Transaction> getDeposits() {
        return deposits;
    }

    /**
     * returns a String representation of this Account.
     * 
     * @return String in the form
     *         "name: [name] displayname: [displayname] balance: [balance] interest rate: [interestRate]"
     */
    public String toString() {
        return "name: " + name + " displayname: " + displayName + " balance: "
                + balance + " interest rate: " + interestRate;
    }

}
