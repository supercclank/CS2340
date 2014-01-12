package com.example.financialplanner;

import java.util.ArrayList;

import com.example.financialplanner.Transaction.TransactionType;
//Class that defines an account, keeps track of information specific to this account
//as well as providing a transaction, deposit, and withdrawal history
public class Account {
	private String name;
	private String displayName;
	private double balance;
	private double interestRate;
	private ArrayList<Transaction> transactions;
	private ArrayList<Transaction> deposits;
	private ArrayList<Transaction> withdrawals;
	public Account(String name, String displayName, double balance, double interestRate){
		transactions = new ArrayList<Transaction>();
		this.name = name;
		this. displayName = displayName;
		this.balance = balance;
		this.interestRate = interestRate;
	}
	//runs transaction and adds it to history
	public boolean performTransaction(Transaction transaction){
		 run(transaction,1);
		 return transactions.add(transaction);
	}
	//adds transaction to correct history (deposit, withdrawal) then performs action
	private void run(Transaction transaction,int direction){
		TransactionType type = transaction.getType();
		if(direction==1){
			if (type==TransactionType.DEPOSIT){
				deposits.add(transaction);
				deposit(transaction.getValue());
			}
			if(type==TransactionType.WITHDRAW){
				withdrawals.add(transaction);
				withdraw(transaction.getValue());
			}
		}
		if(direction==-1){
			if (type==TransactionType.DEPOSIT){
				deposits.remove(transaction);
				deposit(transaction.getValue()*-1);
			}
			if(type==TransactionType.WITHDRAW){
				withdrawals.remove(transaction);
				withdraw(transaction.getValue()*-1);
			}
		}
	}
	//adds deposit to balance
	private void deposit(double deposit){
		balance+=deposit;
	}
	//deducts withdraw from balance
	private void withdraw(double withdraw){
		balance-=withdraw;
	}
	//adds transaction to correct history (deposit, withdrawal) then performs action in reverse
	public void undoTransaction(Transaction transaction){
		run(transaction,-1);
		transactions.remove(transactions);
	}
	//figure out how to deal with this? (monthly interest, assume one month or?)
	public void performGainInterest(){
		
	}
}
