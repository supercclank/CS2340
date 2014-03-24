package com.example.financialplanner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * class responsible for formatting Strings used in generating reports
 * @author Cory Brzycki
 * @version 1.0
 */
public class ReportFormatter {
	private Register register;
	/**
	 * Constructor for ReportFormatter 
	 * @param Register register - the register object this is responsible for forming in to a report
	 */
	public ReportFormatter(Register register){
		this.register = register;
	}
	/**
	 * Method to generate a formatted list of withdrawals between a date range
	 * @param Date startDate - the left bound of the date range
	 * @param Date endDate - the right bound of the date range
	 * @return List<String> entries - the formatted list with the relevent information found in the register for the provided date range
	 */
	public List<String> getWithdrawList(Date startDate, Date endDate) {
		User u = register.getUser();
		List<String> entries = new ArrayList<String>();
		entries.add(u.getUserName());
		List<Account> accounts = u.getAccounts();
		for (Account a : accounts) {
			String entry = "";
			entry = a.getName() + " " + a.getDisplayName() +" "+a.getBalance();
			entries.add(entry);
			List<Transaction> transactions = a.getDeposits();
			for (Transaction t : transactions) {
				if (t.processedBetween(startDate, endDate)){
					entries.add(t.toString());
				}
			}
		}
		return entries;
	}
	/**
	 * Method to generate a formatted list of deposits between a date range
	 * @param Date startDate - the left bound of the date range
	 * @param Date endDate - the right bound of the date range
	 * @return List<String> entries - the formatted list with the relevent information found in the register for the provided date range
	 */
	public List<String> getDepositList(Date startDate, Date endDate) {
		User u = register.getUser();
		List<String> entries = new ArrayList<String>();
		entries.add(u.getUserName());
		List<Account> accounts = u.getAccounts();
		for (Account a : accounts) {
			String entry = "";
			entry = a.getName() + " " + a.getDisplayName() +" "+a.getBalance();
			entries.add(entry);
			List<Transaction> transactions = a.getWithdrawals();
			for (Transaction t : transactions) {
				if (t.processedBetween(startDate, endDate)){
					entries.add(t.toString());
				}
			}
		}
		return entries;
	}
	/**
	 * Method to generate a formatted list of transactions between a date range
	 * @param Date startDate - the left bound of the date range
	 * @param Date endDate - the right bound of the date range
	 * @return List<String> entries - the formatted list with the relevent information found in the register for the provided date range
	 */
	public List<String> getTransactionList(Date startDate, Date endDate) {
		User u = register.getUser();
		List<String> entries = new ArrayList<String>();
		entries.add(u.getUserName());
		List<Account> accounts = u.getAccounts();
		for (Account a : accounts) {
			String entry = "";
			entry = a.getName() + " " + a.getDisplayName() +" "+a.getBalance();
			entries.add(entry);
			List<Transaction> transactions = a.getTransactions();
			for (Transaction t : transactions) {
				if (t.processedBetween(startDate, endDate)){
					entries.add(t.toString());
				}
			}
		}
		return entries;
	}
}
