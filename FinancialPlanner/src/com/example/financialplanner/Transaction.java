package com.example.financialplanner;

import java.util.Date;
import java.util.UUID;
/**
 * class responsible for holding information necessary to a transaction
 * @author Cory Brzycki
 * @version 1.0
 */
public class Transaction {
	private TransactionType type;
	private double value;
	private Date dateAdded;
	private Date dateProcessed;
	private String description;
	private final UUID transactionID;
	//separate into two separate classes
	public enum TransactionType {WITHDRAW, DEPOSIT}

	/**
	 * Constructs a new Transaction.
	 *
	 * @param TransactionType type - the type of Transaction
	 * @param double value - the monetary value of the Transaction
	 * @param Date dateAdded - the Date this Transaction was made
	 * @param Date dateProcessed - the Date this Transaction was processed
	 * @param String description - a description for this Transaction
	 */
	public Transaction(TransactionType type, double value, Date dateAdded,
			Date dateProcessed, String description) {
		this.type = type;
		this.value = value;
		this.dateAdded = dateAdded;
		this.dateProcessed = dateProcessed;
		this.description = description;
		transactionID = UUID.randomUUID();

	}
	/**
	 * returns a String representation of this Transaction
	 * @return String in the form "Description: [description] Type: [type] Value: [value] Date Added: [dateAdded] Date Processed: [dateProcessed]"
	 */
	public String toString(){
		return "Description: "+description+" Type: "+type+" Value: "+value+" Date Added: "+dateAdded+" Date Processed: "+dateProcessed;
	}
	/*NOTE date objects need to be made as follows*
	Calendar c = new GregorianCalendar().getInstance();
	c.set(2014, Calender.MONTH, DATE);
	Date d = c.getTime();
	*/
	/**
	 * getter for TransactionType
	 * @return TransactionType type - this Transaction's type
	 */
	public TransactionType getType() {
		return type;

	}
	/**
	 * getter for value
	 * @return double value - the monetary value of this Transaction
	 */
	public double getValue() {
		return this.value;

	}
	/**
	 * getter for dateAdded
	 * @return	Date dateAdded - the Date this Transaction was made
	 */
	public Date getDateAdded() {
		return this.dateAdded;

	}
	/**
	 * getter for DateProcessed
	 * @return Date dateProcessed - the Date this Transaction was processed
	 */
	public Date getDateProcessed() {
		return this.dateProcessed;

	}
	/**
	 * getter for description
	 * @return String description - this Transaction's description
	 */
	public String getDescription() {
		return this.description;

	}

	/**
	 * getter for transactionID.
	 * @return String representation of transactionID -	this Transaction's transaction ID in String form
	 */
	public String getTransactionID() {
		return this.transactionID.toString();

	}

	// Checks to see if this transaction took place between a date range
	/**
	 * private helper method to see if a date falls between a date range
	 * @param Date dateOfInterest - the date to check
	 * @param Date startDate - the left bound of the range
	 * @param Date endDate - the right bound of the range
	 * @return
	 */
	private boolean fallsBetween(Date dateOfInterest, Date startDate, Date endDate) {
		if (dateOfInterest.compareTo(startDate) < 0) {
			return false;

		}
		if (dateOfInterest.compareTo(endDate) > 0) {
			return false;
		}
		return true;

	}
	/**
	 * Checks if this Transaction was processed in a specified range of dates.
	 * @param Date startDate - the lower bound for the date range
	 * @param DateendDate - the upper bound for the date range
	 * @return	true if the Transaction was processed within the specified
	 * 			period of time
	 * 				false otherwise
	 */
	public boolean processedBetween(Date startDate, Date endDate) {
		return this.fallsBetween(this.dateProcessed, startDate, endDate);

	}
	/**
	 * Checks if this Transaction was added in a specified range of dates.
	 * @param Date startDate - the lower bound for the date range
	 * @param Date endDate - the upper bound for the date range
	 * @return	true if the Transaction was added within the specified period
	 * 			of time
	 * 				false otherwise
	 */
	public boolean addedBetween(Date startDate, Date endDate) {
		return this.fallsBetween(this.dateAdded, startDate, endDate);

	}
}
