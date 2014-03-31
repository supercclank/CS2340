package com.example.financialplanner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Class that is responsible for creating and updating database.
 * @author Cory
 *
 */
public class DatabaseInterface extends SQLiteOpenHelper {
    /**
     * The name of the table that this app uses.
     */
  //CHECKSTYLE.OFF: String literal - string literal use of register needed
    public static final String TABLE_REGISTER = "register";
  //CHECKSTYLE.ON: String literal
    /**
     * The name of the column of interest in the table this app uses.
     */
  //CHECKSTYLE:OFF - string literal use of register needed
    public static final String COLUMN_REGISTER = "register";
  //CHECKSTYLE:ON  
    /**
     * Name of the database this app uses.
     */
    private static final String DATABASE_NAME = "register.db";
    /**
     * The version of hte database this app uses.
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Sql query that creates the database for this app.
     */
    private static final String DATABASE_CREATE = "create table "
        + TABLE_REGISTER + "(" + COLUMN_REGISTER + " TEXT);";

    /**
     * Method that uses a super call to create databasr.
     * @param context - the instance of the app creating the databse
     */
    public DatabaseInterface(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        System.out.println("ran to here");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseInterface.class.getName(),
            "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTER);
        onCreate(db);
    }

} 