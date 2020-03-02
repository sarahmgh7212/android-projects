package com.sarah.assignment2_s3667123;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.ListView;

import com.sarah.assignment2_s3667123.adapter.ListView3Adapter;
import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.view.EditListItemActivity;
import com.sarah.assignment2_s3667123.view.ListView3Activity;
import com.sarah.assignment2_s3667123.view.WeekActivity;

import android.view.Menu;
import android.view.MenuItem;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MonthDetailActivity extends AppCompatActivity{
    // Class to show the month
    private Map<String, Event> events;
    private String[] mMonthList = {   "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private int intSelectedMonth;
    private String selectedMonth;
    Bundle bundle;
    private ListView listView;
    private String itemId = null;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // click functionality with event list
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view3);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            intSelectedMonth = bundle.getInt("passingMonth");
        }

        FileModel fileModel = FileModelImplement.getSingletonInstance(this);
        events = fileModel.getEventList();


        Map<String, Event> list=new HashMap<>();

        for (Map.Entry<String, Event> entry : events.entrySet()) {
            Event e = entry.getValue();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(e.getEventStartDate());
            if(intSelectedMonth == calendar.get(Calendar.MONTH)) {
                list.put(entry.getKey(),entry.getValue());
            }
        }
        ListView3Adapter mAdapter;
        listView = findViewById(R.id.myListView3);
        mAdapter = new ListView3Adapter(MonthDetailActivity.this,  list);
        listView.setAdapter(mAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_event:
                Intent editItemIntent = new Intent(MonthDetailActivity.this, EditListItemActivity.class);
                editItemIntent.putExtra(Intent.EXTRA_TEXT, itemId);
                editItemIntent.setType("text/plain");

                if(editItemIntent.resolveActivity(this.getPackageManager()) != null) {
                    this.startActivity(editItemIntent);
                } else {
                    Log.i("", "Cannot open activity for this intent");
                }
                return (true);
            case R.id.sort:
                Intent editItemIntent1 = new Intent(MonthDetailActivity.this, ListView3Activity.class);
                editItemIntent1.putExtra(Intent.EXTRA_TEXT, "SortAsc");
                editItemIntent1.setType("text/plain");
                if (editItemIntent1.resolveActivity(this.getPackageManager()) != null) {
                    this.startActivity(editItemIntent1);
                } else {
                    Log.i("", "Cannot open activity for this intent");
                }
                return (true);
            case R.id.desc:
                Intent editItemIntent2 = new Intent(MonthDetailActivity.this, ListView3Activity.class);
                editItemIntent2.putExtra(Intent.EXTRA_TEXT, "SortDesc");
                editItemIntent2.setType("text/plain");
                if (editItemIntent2.resolveActivity(this.getPackageManager()) != null) {
                    this.startActivity(editItemIntent2);
                } else {
                    Log.i("", "Cannot open activity for this intent");
                }

                return (true);
            case R.id.monthCalender:
                Intent intent3 = new Intent(MonthDetailActivity.this, MonthActivity.class);
                startActivity(intent3);
                return (true);
            case R.id.weekCalender:
                Intent intent4 = new Intent(MonthDetailActivity.this, WeekActivity.class);
                startActivity(intent4);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}
