package com.example.financialplanner;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RegisterDataSource {

  // Database fields
  private SQLiteDatabase database;
  private DatabaseInterface dbHelper;
  private String[] allColumns = {DatabaseInterface.COLUMN_register};

  public RegisterDataSource(Context context){
	  dbHelper = new DatabaseInterface(context);
	  database = dbHelper.getWritableDatabase();
  }
  public void addRegister(String register) {
	    ContentValues values = new ContentValues();
	    values.put("register", register); // Contact Name
	    // Inserting Row
	    database.insert(DatabaseInterface.TABLE_REGISTER, null, values);
	    database.close(); // Closing database connection
	}
  public void updateRegister(String register) {
	  ContentValues values = new ContentValues();
	    values.put("register", register);
	    database.update(DatabaseInterface.TABLE_REGISTER, values, null, null);
  }
  // Getting single contact
public String getRegister(String regString) {
  String registerString = null;
  Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseInterface.TABLE_REGISTER, null);
  if (cursor.moveToFirst()){
	  registerString = cursor.getString(0);
  }
  return registerString;
}
  
} 