package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

/*
 * Used Developer Documentation https://developer.android.com/training/data-storage/sqlite#java
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //Creating and initializing variables
    public static final String DATABASE_NAME="register.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "register";
    public static final String ID = "ID";
    public static final String FirstName = "FirstName";
    public static final String LastName = "LastName";
    public static final String Password = "Password";
    public static final String Email = "Email";
    public static final String DOB = "DOB";

    //This string will hold my query to create the registration table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FirstName+ " TEXT," +
                    LastName+ " TEXT," +
                    Password+ " TEXT," +
                    Email+ " TEXT," +
                    DOB+ " TEXT)";

    //String holds query to drop registration table
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Class Constructor
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operation", "Database Created...");
    }

    //onCreate will execute my query to create register Table
    @Override
    public void onCreate(SQLiteDatabase db)  {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("Database Operations", "Database Created...");
    }

    //onUpgrade will execute my query to delete register table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    //addData to our register table
    public boolean addData(String fn, String ln, String pw, String em, String dob){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FirstName, fn);
        contentValues.put(LastName, ln);
        contentValues.put(Password, pw);
        contentValues.put(Email, em);
        contentValues.put(DOB, dob);

        Log.d(TAG, "addData: Adding" + fn +" "+ ln +" "+ pw +" "+ em +" "+ dob + " to " + TABLE_NAME);

        //Will return -1 if insert fails, otherwise returns 1 i believe
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result== -1){
            return false;
        }else{
            return true;
        }
    }

    //This is used for login
    public boolean checkData(String em, String pw){
        SQLiteDatabase db = this.getReadableDatabase();

        // The array of columns to return (pass null to get all)
        String[] projection = {
                Password
        };

        //selection is column for my WHERE clause
        //selectionArghs is value for my WHERE clause
        String selection = Email + " = ?";
        String[] selectionArgs = { em };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = Password + " DESC";

        //SELECT PASSWORD FROM register WHERE EMAIL = "em"
        Cursor cursor = db.query(
                TABLE_NAME,             // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                sortOrder              // The sort order
        );

        //IF CURSOR is null||EMPTY RETURN FALSE
        if (cursor==null || cursor.getCount()== 0){
            return false;
        }

        //move to head of cursor -1 -> 0
        cursor.moveToNext();
        String grabPW = cursor.getString(cursor.getColumnIndexOrThrow(Password));   //pull value

        //Compare password, NOTE: not hashing passwords atm (not required by project)
        if (pw.equals(grabPW)){
            return true;
        }else return false;
    }
}