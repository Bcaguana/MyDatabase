package com.example.mydatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText et_Name, et_Age;
    SwitchCompat sw_customer;
    Button btn_Add, btn_ViewAll;
    ListView lv_data;

    ArrayAdapter customerArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btn_ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Viewing All Data", Toast.LENGTH_SHORT).show();

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                showCustomerOnListView(databaseHelper);

            }
        });
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerModel customer;
                //Add entry before clearing fields
                try {
                    customer = new CustomerModel(-1, et_Name.getText().toString(), Integer.parseInt(et_Age.getText().toString()), sw_customer.isActivated());
                    Toast.makeText(MainActivity.this, customer.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    customer = new CustomerModel(-1, "error", 0, false);
                    Toast.makeText(MainActivity.this, "Error Adding Entry", Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean success = databaseHelper.addOne(customer);
                Toast.makeText(MainActivity.this, "Success: " + success, Toast.LENGTH_SHORT).show();
                showCustomerOnListView(databaseHelper);

                et_Age.setText("");
                et_Name.setText("");
                sw_customer.setChecked(false);
            }
        });
        lv_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomerModel clickedCustomer = (CustomerModel) adapterView.getItemAtPosition(i);
                databaseHelper.deleteOne(clickedCustomer);
                showCustomerOnListView(databaseHelper);

            }
        });
    }

    private void showCustomerOnListView(DatabaseHelper databaseHelper) {
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, databaseHelper.getEveryone());
        lv_data.setAdapter(customerArrayAdapter);
    }

    private void initViews() {
        et_Name = findViewById(R.id.et_Name);
        et_Age = findViewById(R.id.et_Age);
        sw_customer = findViewById(R.id.sw_customer);
        btn_Add = findViewById(R.id.btn_Add);
        btn_ViewAll = findViewById(R.id.btn_ViewAll);
        lv_data = findViewById(R.id.lv_data);

        databaseHelper = new DatabaseHelper(MainActivity.this);
    }


}