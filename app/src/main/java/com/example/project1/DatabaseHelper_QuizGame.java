package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/*
 * Used Devloper Documentation https://developer.android.com/training/data-storage/sqlite#java
 */
public class DatabaseHelper_QuizGame extends SQLiteOpenHelper {

    //Creating and initializing variables
    public static final String DATABASE_NAME="QuizGame.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "QuizGame";
    public static final String ID = "ID";
    public static final String Email = "Email";
    public static final String Score= "Score";
    public static final String Date ="Date";

    //This string will hold my query to create the QuizGame table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Email+ " TEXT," +
                    Score+ " INT," +
                    Date+ " TEXT)";

    //String holds query to drop table
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Class Constructor
    public DatabaseHelper_QuizGame(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Operation", "Database Created...");
    }

    //onCreate will execute my query to create register Table
    @Override
    public void onCreate(SQLiteDatabase db)  {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d("Database Operations:", "Quiz Game Database Created...");
    }

    //onUpgrade will execute my query to delete register table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    //addData to our Quiz Game table
    public boolean addData(String em, int score, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Email, em);
        contentValues.put(Score, score);
        contentValues.put(Date, date);

        Log.d(TAG, "addData: Adding" + em +" "+ score +" "+ date + " to " + TABLE_NAME);

        //Will return -1 if insert fails, otherwise returns 1 i believe
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result== -1){
            return false;
        }else{
            return true;
        }
    }


    public ArrayList<QuizGame> getAllData(){
        //Get email
        String e = CardquizActivity.passEmail();
        //Store email inside array, rawQuery doesn't seem to like var strings
        String [] ee = new String[]{e};
        ArrayList<QuizGame> arrayList = new ArrayList<>();
        //Need this to read db
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM QuizGame WHERE Email =?", ee ,null);
        //move head while true
        while(cursor.moveToNext()){
            //pull values
            int score = cursor.getInt(2);
            String date = cursor.getString(3);
            //store values
            QuizGame qG = new QuizGame(score, date);

            arrayList.add(qG);
        }
        return arrayList;
    }
}