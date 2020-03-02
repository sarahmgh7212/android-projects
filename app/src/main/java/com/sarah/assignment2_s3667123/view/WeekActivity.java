package com.sarah.assignment2_s3667123.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sarah.assignment2_s3667123.R;

public class WeekActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    // Class to show the week Calender
    private ListView mList;
    private String[] mWeekList = {
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        mList = findViewById(R.id.week_list);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mWeekList);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // onclick functionality for week.
        Intent intent = new Intent(WeekActivity.this, WeekDetailActivity.class);
        intent.putExtra("passingWeek", i);
        startActivity(intent);

    }
}
