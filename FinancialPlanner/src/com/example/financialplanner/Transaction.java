package com.example.financialplanner;

import java.util.Date;
import java.util.UUID;
/**
 * class responsible for holding information necessary to a transaction.
 * @author Cory Brzycki
 * @version 1.0
 */
public class Transaction {
    /**
     * the type of this transaction.
     */
    private TransactionType type;
    /**
     * the monetary value of this transaction.
     */
    private double value;
    /**
     * the date that this transaction was added.
     */
    private Date dateAdded;
    /**
     * the date that this transaction was processed.
     */
    private Date dateProcessed;
    /**
     * the description of this transaction.
     */
    private String description;
    /**
     * the transactionID of this transaction.
     */
    private final UUID transactionID;
    //separate into two separate classes
    /**
     * enum for use to signify if this transaction is a withdraw or deposit.
     * @author Cory Brzycki
     */
    public enum TransactionType { WITHDRAW, DEPOSIT }

    /**
     * Constructs a new Transaction.
     *
     * @param typeP - the type of Transaction
     * @param valueP - the monetary value of the Transaction
     * @param dateAddedP - the Date this Transaction was made
     * @param dateProcessedP - the Date this Transaction was processed
     * @param descriptionP - a description for this Transaction
     */
    public Transaction(TransactionType typeP, double valueP, Date dateAddedP,
            Date dateProcessedP, String descriptionP) {
        this.type = typeP;
        this.value = valueP;
        this.dateAdded = dateAddedP;
        this.dateProcessed = dateProcessedP;
        this.description = descriptionP;
        transactionID = UUID.randomUUID();

    }
    /**
     * returns a String representation of this Transaction.
     * @return String in the form "Description: [description] Type: [type] Value: [value] Date Added: [dateAdded] Date Processed: [dateProcessed]"
     */
    public String toString() {
        return "Description: " + description + " Type: " + type + " Value: " + value + " Date Added: " + dateAdded + " Date Processed: " + dateProcessed;
    }
    /*NOTE date objects need to be made as follows*
    Calendar c = new GregorianCalendar().getInstance();
    c.set(2014, Calender.MONTH, DATE);
    Date d = c.getTime();
    */
    /**
     * getter for TransactionType.
     * @return TransactionType type - this Transaction's type
     */
    public TransactionType getType() {
        return type;

    }
    /**
     * getter for value.
     * @return double value - the monetary value of this Transaction
     */
    public double getValue() {
        return this.value;

    }
    /**
     * getter for dateAdded.
     * @return    Date dateAdded - the Date this Transaction was made
     */
    public Date getDateAdded() {
        return this.dateAdded;

    }
    /**
     * getter for DateProcessed.
     * @return Date dateProcessed - the Date this Transaction was processed
     */
    public Date getDateProcessed() {
        return this.dateProcessed;

    }
    /**
     * getter for description.
     * @return String description - this Transaction's description
     */
    public String getDescription() {
        return this.description;

    }

    /**
     * getter for transactionID.
     * @return String representation of transactionID -    this Transaction's transaction ID in String form
     */
    public String getTransactionID() {
        return this.transactionID.toString();

    }

    // Checks to see if this transaction took place between a date range
    /**
     * private helper method to see if a date falls between a date range.
     * @param dateOfInterest - the date to check
     * @param startDate - the left bound of the range
     * @param endDate - the right bound of the range
     * @return true if dateOfInterest falls between startDate and endDate
     *           false otherwise
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
     * @param startDate - the lower bound for the date range
     * @param endDate - the upper bound for the date range
     * @return    true if the Transaction was processed within the specified
     *             period of time
     *                 false otherwise
     */
    public boolean processedBetween(Date startDate, Date endDate) {
        return this.fallsBetween(this.dateProcessed, startDate, endDate);

    }
    /**
     * Checks if this Transaction was added in a specified range of dates.
     * @param startDate - the lower bound for the date range
     * @param endDate - the upper bound for the date range
     * @return    true if the Transaction was added within the specified period
     *             of time
     *                 false otherwise
     */
    public boolean addedBetween(Date startDate, Date endDate) {
        return this.fallsBetween(this.dateAdded, startDate, endDate);

    }
}
