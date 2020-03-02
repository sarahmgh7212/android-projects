package com.sarah.assignment2_s3667123.sqlite_database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.sarah.assignment2_s3667123.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {


    SQLiteDatabase myDB;
    ListView listViewEmp;
    EmployeeAdapter employeeAdapter;
    List<Employee> employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        listViewEmp = (ListView) findViewById(R.id.listViewEmployees);
        employeeList = new ArrayList<>();

        //opening the database
        myDB = openOrCreateDatabase(SQLiteMainActivity.DATABASE_NAME, MODE_PRIVATE, null);

        //this method will display the employees in the list
        displayEmployeesFromDB();
    }

    private void displayEmployeesFromDB() {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursor = myDB.rawQuery("SELECT * FROM employees", null);

        //if the cursor has some data
        if (cursor.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                employeeList.add(new Employee(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4)
                ));
            } while (cursor.moveToNext());
        }
        //closing the cursor
        cursor.close();

        //creating the employeeAdapter object
        employeeAdapter = new EmployeeAdapter(this, R.layout.list_layout_employee, employeeList, myDB);

        //adding the employeeAdapter to listview
        listViewEmp.setAdapter(employeeAdapter);
    }

}
