package com.example.financialplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * Class used by the user interface to indirectly update database.
 * @author Cory
 *
 */
public class RegisterDataSource {

  // Database fields
    /**
     * The database that this class is dealing with.
     */
    private SQLiteDatabase database;
    /**
     * the datanase interface this class is using to update the database.
     */
    private DatabaseInterface dbHelper;
    /**
     * Constructor for RegisterDataSource.
     * @param context - the app used to instatiate this class
     */
    public RegisterDataSource(Context context) {
        dbHelper = new DatabaseInterface(context);
        database = dbHelper.getWritableDatabase();
    }
    /**
     * Method that adds a record to the database.
     * @param register - the register String to add
     */
    public void addRegister(String register) {
        ContentValues values = new ContentValues();
        //CHECKSTYLE.OFF: String literal - need string literal use of register
        values.put("register", register); // Contact Name
      //CHECKSTYLE.ON: String literal
            // Inserting Row
        database.insert(DatabaseInterface.TABLE_REGISTER, null, values);
        database.close(); // Closing database connection
    }
    /**
     * Method that updates a record in the database.
     * @param register - the register String to be used in update
     */
    public void updateRegister(String register) {
        ContentValues values = new ContentValues();
        values.put("register", register);
        database.update(DatabaseInterface.TABLE_REGISTER, values, null, null);
        database.close();
    }
      // Getting single contact
    /**
     * Method that gets the first record in a database.
     * @param regString - key of the record to be fetched
     * @return string representation of the fetched register
     */
    public String getRegister(String regString) {
        String registerString = null;
        Cursor cursor = database.rawQuery("SELECT * FROM " + DatabaseInterface.TABLE_REGISTER, null);
        if (cursor.moveToFirst()) {
            registerString = cursor.getString(0);
        }
        return registerString;
    }
      
} 