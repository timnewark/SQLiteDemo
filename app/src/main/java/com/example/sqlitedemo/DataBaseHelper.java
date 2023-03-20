package com.example.sqlitedemo;


//handles all the operations

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
//has some methods that have to be implemented

public class DataBaseHelper extends SQLiteOpenHelper{
//list of constants below
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";
//end of constants list
    //costructur satying what the database will be named etc
    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    // this is called the first time a database is accessed. there should be code in here to create a new database
    @Override
    public void onCreate(SQLiteDatabase db){ //method will be called first time DB is referenced
        //check for spaces. use ctrl/alt and C to refactor into a
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_NAME + " TEXT, " + COLUMN_CUSTOMER_AGE + " INT, " + COLUMN_ACTIVE_CUSTOMER + " BOOL) ";
        db.execSQL(createTableStatement); //this will execute the statement string. DB is a parameter passed from above

    }
        // called whenever the version number of my database changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public boolean addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase(); //getWritable for instert/getReadable for select (read)
        ContentValues cv = new ContentValues(); //creation of ontent values. next line wwe will put it all in
        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());
//ID is not in here because its an automatically incrementing value

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
        //this is the full addOne method
    }

    //below will get all the data from the SQL DB. this will be exe by the viewall button
    public List<CustomerModel> getEveryone(){
        List<CustomerModel> returnList = new ArrayList<>();
        // get data from the database
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

//gets the database
        SQLiteDatabase db= this.getReadableDatabase();

        // cursor is the resultset from a DB query (complex array of items)
        Cursor cursor = db.rawQuery(queryString, null);

        //moveToFirst will move to first result in resultset
        if (cursor.moveToFirst()) {

            // loop through the cursor (resultset) and create new customer objects/then put them into return list
            //now we will have a do while. So do brackets while brackets
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1 ? true : false;//this last bit is a ternary operator

                CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerAge, customerActive);
                returnList.add(newCustomer);
            }
            while (cursor.moveToNext());

        }
        else {//failure. do not add anything to the list


        }//end of if
//in SQLite you have to close the database after use.
        cursor.close();
        db.close();
        return returnList;
    }
}

