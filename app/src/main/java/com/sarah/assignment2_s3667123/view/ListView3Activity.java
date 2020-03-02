package com.sarah.assignment2_s3667123.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;

import com.sarah.assignment2_s3667123.MonthActivity;
import com.sarah.assignment2_s3667123.R;
import com.sarah.assignment2_s3667123.adapter.ListView3Adapter;
import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.viewmodel.ItemsListViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListView3Activity extends AppCompatActivity {
    // Class to display the event list view
    Button addEventButton;
    String itemId = null;
    Button sortButton;
    Button descSortButton;
    String flag ="no";
    public static final String DATABASE_NAME = "MoviePlannerDatabase";
    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view3);
        Intent intent = getIntent();
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        createEventTable();
       // descSortButton.setOnClickListener(new SortEventListener(this));
        if (intent.getExtras() != null) {
            flag = (String) intent.getExtras().get(Intent.EXTRA_TEXT);
        }

        ItemsListViewModel myViewModel = ViewModelProviders.of(this).get(ItemsListViewModel.class);

        myViewModel.getItems().observe(this, new Observer<Map<String, Event>>() {
            @Override
            public void onChanged(Map<String, Event> items) {
                // Update your UI with the loaded data.
                // Returns cached data automatically after a configuration change
                // and this method will be called again if underlying Live Data object is modified
                Set<Map.Entry<String, Event>> entries = items.entrySet();
                List<Map.Entry<String, Event>> listOfEntries = new ArrayList<Map.Entry<String, Event>>(entries); // sorting HashMap by values using comparator
                if (flag.equalsIgnoreCase("SortAsc")) {
                    Collections.sort(listOfEntries, new EventNameComparator());
                }else if(flag.equalsIgnoreCase("SortDesc")){
                    Collections.sort(listOfEntries, new EventNameComparator(
                            EventNameComparator.REVERSE_ORDER));

                }
                LinkedHashMap<String, Event> sortedByValue = new LinkedHashMap<String, Event>(listOfEntries.size());
                for (Map.Entry<String, Event> entry : listOfEntries) {
                    sortedByValue.put(entry.getKey(), entry.getValue());
                }
                ListView3Adapter mAdapter;

                if (flag.equalsIgnoreCase("SortAsc") || flag.equalsIgnoreCase("SortDesc") ) {
                    mAdapter = new ListView3Adapter(ListView3Activity.this, sortedByValue);
                } else {
                    mAdapter = new ListView3Adapter(ListView3Activity.this, items);
                }
                ListView myListView3 = findViewById(R.id.myListView3);
                myListView3.setAdapter(mAdapter);

            }
        });
    }

    private void createEventTable() {
//        mDatabase.execSQL(
//                String.format("CREATE TABLE IF NOT EXISTS event (\neventId INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY,\n eventTitle varchar(200) NOT NULL," +
//                        "\nstartDate varchar(200) NOT NULL,\nendDate varchar(200) NOT NULL,\nvenue varchar(200) NOT NULL," +
//                        "\nlocation varchar(200) NOT NULL,\nmovie varchar(200) NULL,\n attendence varchar(200) NULL," +
//                        "\n attendenceCount INTEGER NULL);")
//        );
        mDatabase.execSQL(
                String.format(
                            "CREATE TABLE IF NOT EXISTS movies (\n" +
                            "    movieid varchar(200) NOT NULL CONSTRAINT employees_pk PRIMARY KEY ,\n" +
                            "    movietitle varchar(200) NOT NULL,\n" +
                            "    year varchar(200) NOT NULL,\n" +
                            "    poster varchar(200) NOT NULL\n" +
                            ");"
                    )
                 );

        mDatabase.execSQL(
                String.format(
                            "CREATE TABLE IF NOT EXISTS events (\n" +
                                "    eventid varchar(200) NOT NULL CONSTRAINT employees_pk PRIMARY KEY ,\n" +
                                "    title varchar(200) NOT NULL,\n" +
                                "    startdate varchar(200) NOT NULL,\n" +
                                "    endDate varchar(200) NOT NULL,\n" +
                                "    venue varchar(200) NOT NULL,\n" +
                                "    location varchar(200) NOT NULL,\n" +
                                "    movie varchar(200) ,\n" +
//                                "    FOREIGN KEY(movie) REFERENCES movies(movieid),\n" +
                                "    attendence varchar(200) ,\n" +
                                "    attendenceCount integer NOT NULL\n" +
                                ");"
                        )
                );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Show the selected menu
        switch (item.getItemId()) {
            case R.id.add_event:
                // Naviagate to create Event Screen.
                Intent editItemIntent = new Intent(this, EditListItemActivity.class);
                editItemIntent.putExtra(Intent.EXTRA_TEXT, itemId);
                editItemIntent.putExtra("FromAddEvent", "ADD Event");
                editItemIntent.setType("text/plain");

                if(editItemIntent.resolveActivity(this.getPackageManager()) != null) {
                    this.startActivity(editItemIntent);
                } else {
                    Log.i("", "Cannot open activity for this intent");
                }
                return (true);
            case R.id.sort:
                // Naviagate to create Event list Screen based on Asc Sort.
                Intent editItemIntent1 = new Intent(this, ListView3Activity.class);
                editItemIntent1.putExtra(Intent.EXTRA_TEXT, "SortAsc");
                editItemIntent1.setType("text/plain");
                if (editItemIntent1.resolveActivity(this.getPackageManager()) != null) {
                    this.startActivity(editItemIntent1);
                } else {
                    Log.i("", "Cannot open activity for this intent");
                }
                return (true);
            case R.id.desc:
                // Naviagate to create Event list Screen based on Desc Sort.
                Intent editItemIntent2 = new Intent(this, ListView3Activity.class);
                editItemIntent2.putExtra(Intent.EXTRA_TEXT, "SortDesc");
                editItemIntent2.setType("text/plain");
                if (editItemIntent2.resolveActivity(this.getPackageManager()) != null) {
                    this.startActivity(editItemIntent2);
                } else {
                    Log.i("", "Cannot open activity for this intent");
                }

                return (true);
            case R.id.monthCalender:
                // Create the month calender.
                Intent mIntent3 = new Intent(ListView3Activity.this, MonthActivity.class);
                startActivity(mIntent3);
                return (true);
            case R.id.weekCalender:
                // Create the week calender.
                Intent mIntent4 = new Intent(ListView3Activity.this, WeekActivity.class);
                startActivity(mIntent4);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}

class EventNameComparator implements Comparator<Map.Entry<String, Event>> {
// sort based on asc or desc menu selection.
    public static final int REVERSE_ORDER = 1;
    private int order;

    public EventNameComparator(int order) {
        this.order = order;
    }

    public EventNameComparator() {
        this(0);
    }

    @Override
    public int compare(Map.Entry<String, Event> e1, Map.Entry<String, Event> e2) {
        // Sort the value.
        Date v1 = e1.getValue().getEventStartDate();
        Date v2 = e2.getValue().getEventStartDate();
        if (order == REVERSE_ORDER) {
            return v2.compareTo(v1);
        } else {
            return v1.compareTo(v2);
        }

    }

}




    /*Comparator<Map.Entry<String, Event>> valueComparator = new Comparator<Map.Entry<String,Event>>() {
        public static final int REVERSE_ORDER = 1;
        private int order;

        @Override
        public int compare(Map.Entry<String, Event> e1, Map.Entry<String, Event> e2) {
            Date v1 = e1.getValue().getEventStartDate();
            Date v2 = e2.getValue().getEventStartDate();
            return v1.compareTo(v2);
        }
    };*/

//}
