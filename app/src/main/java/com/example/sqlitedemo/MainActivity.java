package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

//main method
public class MainActivity extends AppCompatActivity{

    // references to buttons and other controls on the layout. Need to be here to be accessible throughout
    Button btn_add, btn_viewAll, btn_numberToFind_Button;
    EditText EditText_Name, EditText_Number, mEditWordView;
    Switch sw_activeCustomer;
    ListView lv_customer_list;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //finding stuff here
    btn_add = findViewById(R.id.btn_add);
    btn_viewAll = findViewById(R.id.btn_viewAll);
    btn_numberToFind_Button = findViewById(R.id.btn_numberToFind_Button);
    EditText_Name = findViewById(R.id.EditText_Name);
    mEditWordView = findViewById(R.id.EditText_searchWordInput);
    EditText_Number = findViewById(R.id.EditText_Number);
    sw_activeCustomer = findViewById(R.id.sw_active);
    lv_customer_list = findViewById(R.id.lv_customer_list);

    dataBaseHelper = new DataBaseHelper(MainActivity.this);

    //arrayadapter here to show it as soon as app is opened (this has been refactored into a method)
    ShowCustomersOnListView(dataBaseHelper);
    //finding stuff end

    //SETTING THE CLICK LISTENERS for the add & view all button
        // ADD BUTTON
        btn_add.setOnClickListener(v -> {

            //below creating newcustomer reference from CustomerModel.java
            //(int id, String name, int age, boolean isActive)
            //this will get all the information from the entry form and create a new customr model from it

            CustomerModel customerModel; //null value

            try {           //creating a try and catch here to get the fatal exception to NOT HAPPEN
                 customerModel = new CustomerModel(-1, EditText_Name.getText().toString(), Integer.parseInt(EditText_Number.getText().toString()), sw_activeCustomer.isChecked());
                //below toast modified to use the customerModel above instead of normal text
                Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(MainActivity.this, "error creating customer", Toast.LENGTH_SHORT).show();
                customerModel = new CustomerModel(-1, "error", 0, false);

            }

            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

            boolean success = dataBaseHelper.addOne(customerModel);
            Toast.makeText(MainActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();

            //this will update the arrayAdapter AFTER success. remember its been refactored
            ShowCustomersOnListView(dataBaseHelper);


        });

        btn_viewAll.setOnClickListener(v -> {

            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

            ShowCustomersOnListView(dataBaseHelper);

            //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

            // we have created the associated arrayadapter above

        });

        btn_numberToFind_Button.setOnClickListener(v -> {

            String nameString = mEditWordView.getText().toString();
            Toast.makeText(MainActivity.this, "number given is" +  nameString, Toast.LENGTH_LONG).show();
            // functionality here to send namestring
            searchNumber varSendToSetter = new searchNumber(nameString); //call the class, name the obj myObj, declare it as new, send param nameString to it
            varSendToSetter.setSearchNumber(nameString); // Set the value of the setSearchNumber variable to "user inputted value of nameString
            String returnedFromGetter =  varSendToSetter.getSearchNumber();

            //Toast.makeText(MainActivity.this, "ran through get/set:" + myObj, Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "retGet" + returnedFromGetter, Toast.LENGTH_SHORT).show();




            dataBaseHelper.getEveryone2();
            ShowCustomersOnListView2(dataBaseHelper);

            //new from





        });


        lv_customer_list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //setOnItemClickListener is DIFFERENT
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickedCustomer = (CustomerModel) parent.getItemAtPosition(position);
                dataBaseHelper.deleteOne(clickedCustomer);
                ShowCustomersOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, "deleted " +clickedCustomer, Toast.LENGTH_SHORT).show();
            }
            //search button below

        });
    }//leave this one

    //the method below was refactored because it was referenced in three places
    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        lv_customer_list.setAdapter(customerArrayAdapter);
        //Toast.makeText(MainActivity.this, "test " , Toast.LENGTH_SHORT).show();

    }
    private void ShowCustomersOnListView2(DataBaseHelper dataBaseHelper) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone2());
        lv_customer_list.setAdapter(customerArrayAdapter);
        //Toast.makeText(MainActivity.this, "test " , Toast.LENGTH_SHORT).show();
    }
}