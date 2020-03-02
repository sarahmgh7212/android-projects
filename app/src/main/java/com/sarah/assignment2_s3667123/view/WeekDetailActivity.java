package com.sarah.assignment2_s3667123.view;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.sarah.assignment2_s3667123.MonthActivity;
import com.sarah.assignment2_s3667123.R;
import com.sarah.assignment2_s3667123.adapter.ListView3Adapter;
import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
public class WeekDetailActivity extends AppCompatActivity{
    // Class to show the week
    private Map<String, Event> events;
    private String[] mWeekList = {
            "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"
    };
    private int mIntSelectedWeek;
    private String mSelectedWeek;
    Bundle bundle;
    private ListView mList;
    private String itemId = null;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view3);

        bundle = getIntent().getExtras();
        if (bundle != null) {
            mIntSelectedWeek = bundle.getInt("passingWeek");
        }
        FileModel model = FileModelImplement.getSingletonInstance(this);
        events = model.getEventList();
        Map<String, Event> list=new HashMap<>();
        for (Map.Entry<String, Event> entry : events.entrySet()) {
            Event e = entry.getValue();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(e.getEventStartDate());
            if(mIntSelectedWeek+1 == calendar.get(Calendar.DAY_OF_WEEK)) {
                list.put(entry.getKey(),entry.getValue());
                System.out.println("gfghfhggfh"+e.getEventStartDate());
            }
        }
        ListView3Adapter mAdapter;
        mList = findViewById(R.id.myListView3);
        mAdapter = new ListView3Adapter(WeekDetailActivity.this,  list);
        mList.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_event:
                // menu for Add event
                Intent editItemIntent = new Intent(WeekDetailActivity.this, EditListItemActivity.class);
                editItemIntent.putExtra(Intent.EXTRA_TEXT, itemId);
                editItemIntent.setType("text/plain");

                if(editItemIntent.resolveActivity(this.getPackageManager()) != null) {
                    this.startActivity(editItemIntent);
                } else {
                    Log.i("", "Cannot open activity for this intent");
                }
                return (true);
            case R.id.sort:
                // Menu for Asc sort
                Intent editItemIntent1 = new Intent(WeekDetailActivity.this, ListView3Activity.class);
                editItemIntent1.putExtra(Intent.EXTRA_TEXT, "SortAsc");
                editItemIntent1.setType("text/plain");
                if (editItemIntent1.resolveActivity(this.getPackageManager()) != null) {
                    this.startActivity(editItemIntent1);
                } else {
                    Log.i("", "Cannot open activity for this intent");
                }
                return (true);
            case R.id.desc:
                // Menu for Desc sort
                Intent editItemIntent2 = new Intent(WeekDetailActivity.this, ListView3Activity.class);
                editItemIntent2.putExtra(Intent.EXTRA_TEXT, "SortDesc");
                editItemIntent2.setType("text/plain");
                if (editItemIntent2.resolveActivity(this.getPackageManager()) != null) {
                    this.startActivity(editItemIntent2);
                } else {
                    Log.i("", "Cannot open activity for this intent");
                }
                //Intent mIntent = new Intent(ListView3Activity.this, MonthActivity.class);
                //startActivity(mIntent);
                //add the function to perform here
                return (true);
            case R.id.monthCalender:
                Intent mIntent3 = new Intent(WeekDetailActivity.this, MonthActivity.class);
                startActivity(mIntent3);
                return (true);
            case R.id.weekCalender:
                Intent mIntent4 = new Intent(WeekDetailActivity.this, WeekActivity.class);
                startActivity(mIntent4);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}

