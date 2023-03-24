package com.example.sqlitedemo;

import android.util.Log;

public class searchNumber{
    private String mSearchNumber;

//constructor
    public searchNumber(String mSearchNumber) {
        this.mSearchNumber = mSearchNumber;
    }
// constructor end

// getter and setter
    public String getSearchNumber() { //The get method returns the value of the variable.
        return mSearchNumber;

    }

    public void setSearchNumber(String mSearchNumber) { //The set method takes a parameter  and assigns it to the name variable. The this keyword is used to refer to the current object.
        Log.i("mSearchNumber is " , mSearchNumber);
        this.mSearchNumber = mSearchNumber;
    }
// getter and setter end

}

