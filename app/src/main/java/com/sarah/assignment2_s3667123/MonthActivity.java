package com.sarah.assignment2_s3667123;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MonthActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
// Class for the month Activity
    private ListView listView;
    private String[] mMonthList = { "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // create month view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);

        listView = findViewById(R.id.monthList);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mMonthList);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    // on click functionality for month
        Intent intent = new Intent(MonthActivity.this,MonthDetailActivity.class);
        intent.putExtra("passingMonth",i);
        startActivity(intent);

    }
}
