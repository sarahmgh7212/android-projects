package com.sarah.assignment2_s3667123.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.sarah.assignment2_s3667123.R;
import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.model.Movie;
import com.sarah.assignment2_s3667123.model.viewmodel.MoviesListViewModel;
import com.sarah.assignment2_s3667123.adapter.MovieListAdapter;
import java.util.Map;
public class MovieListViewActivity extends AppCompatActivity{
    // Class to edit the movie list
    String eventId = null;
    String timeValue ="";
    String startDateValue ="";
    String endDateValue ="";
    String endTime ="";
    protected String eventTitle = "";
    protected String venue = "";
    protected String location = "";
    protected String Contacts = "";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // View Movie List in the screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view3);
        MoviesListViewModel myViewModel = ViewModelProviders.of(this).get(MoviesListViewModel.class);

        Intent intent = getIntent();
        eventId = (String) intent.getExtras().get(Intent.EXTRA_TEXT);
        timeValue = intent.getExtras().getString("timeValue");
        startDateValue = intent.getExtras().getString("startDateValue");
        endDateValue = intent.getExtras().getString("endDateValue");
        endTime = intent.getExtras().getString("endTime");
        eventTitle = intent.getExtras().getString("eventTitle");
        venue = intent.getExtras().getString("venue");
        location = intent.getExtras().getString("location");
        Contacts = intent.getExtras().getString("Contacts");

        FileModel model = FileModelImplement.getSingletonInstance(getApplicationContext());
        Event event = model.getEventById(eventId);

        myViewModel.getMovies().observe(this, new Observer<Map<String, Movie>>() {
            @Override
            public void onChanged(Map<String, Movie> movies) {
                // Update your UI with the loaded data.
                // Returns cached data automatically after a configuration change
                // and this method will be called again if underlying Live Data object is modified

                MovieListAdapter mAdapter = new MovieListAdapter(MovieListViewActivity.this, movies, eventId,startDateValue,
                        timeValue,endDateValue,endTime,eventTitle,venue,location,Contacts);
                ListView myListView3 = findViewById(R.id.myListView3);
                myListView3.setAdapter(mAdapter);
            }
        });
    }
}

