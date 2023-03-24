package com.example.sqlitedemo;


//handles all the operations

import static android.app.PendingIntent.getActivity;
import static android.content.Intent.getIntent;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

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
    public boolean deleteOne(CustomerModel customerModel){
            // find customer model in the database.  if it is found deleve it and return true.
            //if it is not found return false.
        SQLiteDatabase db = this.getWritableDatabase(); //get Instance of DB
        // will delete so needs to be writable
        String queryString = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + customerModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);//cursor variable required
        if (cursor.moveToFirst()){
            return true;
        }
        else {
            return false;
        }

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
            while (cursor.moveToNext()); // or use this: (c!= null)

        }
        else {//failure. do not add anything to the list


        }//end of if
//in SQLite you have to close the database after use.
        cursor.close();
        db.close();
        return returnList;
    }


    public List<CustomerModel> getEveryone2(){
        List<CustomerModel> returnList = new ArrayList<>(); //creates new array list (for display) (runs with getEveryone in mainActivity)
        // get data from the database

        ;SQLiteDatabase db= this.getReadableDatabase();//opens db gets the database
        //String queryString = "SELECT * FROM CUSTOMER_TABLE where COLUMN_ID LIKE '%"+nameString+"%'";

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
        //String queryString = "SELECT FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + customerModel.getId();
       //THIS ONE ABOVE MAYBE

        //String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_ID + " = " + arg;
        //searchNumber varSendToSetter = new searchNumber(); //call the class, name the obj myObj, declare it as new, send param nameString to it

        //String asfd = egs.getSearchNumber();


        // cursor is the resultset from a DB query (complex array of items)
        Cursor cursor = db.rawQuery(queryString, null);
        Log.i("cursor is " , cursor.toString());
        //Log.i("ret Get Is  " , returnedFromGetter);
//here

        //Log.i("nameString is " , nameString);

        //moveToFirst will move to first result in resultset
        if (cursor.moveToFirst()) {

            // loop through the cursor (resultset) and create new customer objects/then put them into return listnow we will have a do while. So do brackets while brackets
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1 ? true : false;//this last bit is a ternary operator
                //Log.i("namestring is " , nameString);

                for(int i = 0; i<cursor.getCount(); i++)
                {
                    if("3".equals(cursor.getString(0)))
                    {
                        CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerAge, customerActive);
                        returnList.add(newCustomer);


                    }
                }
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

