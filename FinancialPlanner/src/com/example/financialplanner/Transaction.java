package com.example.financialplanner;

import java.util.Calendar;
import java.util.Date;

public class Transaction {
	private TransactionType type;
	private double value;
	private Date dateAdded;
	private Date dateProcessed;
	private String description;
	//enum for enforcement to be used to classify this transaction
	public enum TransactionType {WITHDRAW, DEPOSIT}
	//constructor for transaction
	public Transaction(TransactionType type, double value, Date dateAdded, Date dateProcessed, String description){
		this.type = type;
		this.value = value;
		this.dateAdded = dateAdded;
		this.dateProcessed = dateProcessed;
		this.description = description;
	}
	/*NOTE date objects need to be made as follows*
	Calendar c = new GregorianCalendar().getInstance();
	c.set(2014, Calender.MONTH, DATE);
	Date d = c.getTime();
	*/
	
	
	//returns the type of this transaction
	public TransactionType getType(){
		return type;
	}
	//returns the monetary value of this transaction
	public double getValue(){
		return this.value;
	}
	//returns the dateAdded
	public Date getDateAdded(){
		return this.dateAdded;
	}
	//returns the dateProcessed
	public Date getDateProcessed(){
		return this.dateProcessed;
	}
	//returns the description
	public String getDescription(){
		return this.description;
	}
	//checks to see if this transaction took place between a date range
	private boolean fallsBetween(Date dateOfInterest, Date startDate, Date endDate){
		if(dateOfInterest.compareTo(startDate)<0){
			return false;
		}
		if(dateOfInterest.compareTo(endDate)>0){
			return false;
		}
		return true;
	}
	//checks if this transaction processDate took place between a date range
	public boolean processedBetween(Date startDate, Date endDate){
		return this.fallsBetween(this.dateProcessed, startDate, endDate);
	}
	//checks if this transaction addedDate took place between a date range
		public boolean addedBetween(Date startDate, Date endDate){
			return this.fallsBetween(this.dateAdded, startDate, endDate);
		}
}
