package com.example.financialplanner;

import java.util.Date;
import java.util.UUID;

public class Transaction {
	private TransactionType type;
	private double value;
	private Date dateAdded;
	private Date dateProcessed;
	private String description;
	private final UUID transactionID;

	// enum for enforcement to be used to classify this transaction
	public enum TransactionType {WITHDRAW, DEPOSIT}

	/**
	 * Constructs a new Transaction.
	 *
	 * @param	type			- the type of Transaction
	 * @param	value			- the monetary value of the Transaction
	 * @param	dateAdded		- the Date this Transaction was made
	 * @param	dateProcessed	- the Date this Transaction was processed
	 * @param	description		- a description for this Transaction
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

	/*NOTE date objects need to be made as follows*
	Calendar c = new GregorianCalendar().getInstance();
	c.set(2014, Calender.MONTH, DATE);
	Date d = c.getTime();
	*/
	
	/**
	 * Returns the TransactionType of this Transaction.
	 *
	 * @return	this Transaction's type
	 */
	public TransactionType getType() {
		return type;

	}

	/**
	 * Returns the monetary value of this Transaction.
	 *
	 * @return	the monetary value of this Transaction
	 */
	public double getValue() {
		return this.value;

	}

	/**
	 * Returns the Date this Transaction was made.
	 *
	 * @return	the Date this Transaction was made
	 */
	public Date getDateAdded() {
		return this.dateAdded;

	}

	/**
	 * Returns the Date this Transaction was processed (approved).
	 *
	 * @return	the Date this Transaction was processed
	 */
	public Date getDateProcessed() {
		return this.dateProcessed;

	}

	/**
	 * Returns the description accompanying this Transaction.
	 *
	 * @return	this Transaction's description
	 */
	public String getDescription() {
		return this.description;

	}

	/**
	 * Returns this Transactions's transaction ID.
	 *
	 * @return	this Transaction's transaction ID in String form
	 */
	public String getTransactionID() {
		return this.transactionID.toString();

	}

	// Checks to see if this transaction took place between a date range
	private boolean fallsBetween(Date dateOfInterest, Date startDate,
			Date endDate) {
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
	 *
	 * @param	startDate	- the lower bound for the date range
	 * @param	endDate		- the upper bound for the date range
	 * @return	true if the Transaction was processed within the specified
	 * 			period of time
	 */
	public boolean processedBetween(Date startDate, Date endDate) {
		return this.fallsBetween(this.dateProcessed, startDate, endDate);

	}

	/**
	 * Checks if this Transaction was added in a specified range of dates.
	 *
	 * @param	startDate	- the lower bound for the date range
	 * @param	endDate		- the upper bound for the date range
	 * @return	true if the Transaction was added within the specified period
	 * 			of time
	 */
	public boolean addedBetween(Date startDate, Date endDate) {
		return this.fallsBetween(this.dateAdded, startDate, endDate);

	}

}
