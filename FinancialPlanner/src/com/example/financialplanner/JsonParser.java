package com.example.financialplanner;

public interface JsonParser<T> {
	public T toObject(String json);
	
	public String toJson(T t);
	//figure out what needs to be done here, need to separate things by delimiters, store a section at a time 
	//in a nested String array (array of String arrays of string arrays ect (figure this out later)
}
