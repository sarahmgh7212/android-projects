package com.sarah.assignment2_s3667123.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.model.Movie;
import com.sarah.assignment2_s3667123.view.EditListItemActivity;

import java.util.Map;

public class MovieEditListListener implements View.OnClickListener{
    // Listener to naviagate to Movie List
    private String TAG = getClass().getName();
    private String movieId;
    private Context context;
    private String eventId = null;
    private Map<String, Event> items;
    private String timeValue =null;
    private String startDateValue =null;
    private String endDateValue =null;
    private String endTimeValue =null;
    protected String eventTitle = null;
    protected String venue = null;
    protected String location = null;
    protected String Contacts = null;

    public MovieEditListListener(Context context, String movieId, String eventId,String startDateValue,String timeValue,
                                 String endDateValue,String endTimeValue,String eventTitle,String venue,String location,String Contacts) {
        //Constructor to naviagate to movie List Activity.
        this.context = context;
        this.movieId = movieId;
        this.eventId = eventId;
        this.startDateValue = startDateValue;
        this.timeValue = timeValue;
        this.endDateValue = endDateValue;
        this.endTimeValue = endTimeValue;
        this.eventTitle = eventTitle;
        this.venue = venue;
        this.location = location;
        this.Contacts = Contacts;
    }

    @Override
    public void onClick(View v) {
        // Naviagate to Movie List Activity.
        FileModel model = FileModelImplement.getSingletonInstance(context);
        Event event = model.getEventById(eventId);
        items = model.getEventList();
        Movie movie = model.getMovieById(movieId);

        Intent editItemIntent = new Intent(context, EditListItemActivity.class);
        editItemIntent.putExtra(Intent.EXTRA_TEXT, eventId);

        editItemIntent.putExtra("movieId",movieId);
        editItemIntent.putExtra("endDateValue",endDateValue);
        editItemIntent.putExtra("endTime",endTimeValue);
        editItemIntent.putExtra("timeValue",timeValue);
        editItemIntent.putExtra("startDateValue",startDateValue);
        editItemIntent.putExtra("eventTitle",eventTitle);
        editItemIntent.putExtra("venue",venue);
        editItemIntent.putExtra("location",location);
        editItemIntent.putExtra("Contacts",Contacts);
        editItemIntent.setType("text/plain");

        if(editItemIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(editItemIntent);
        } else {
            Log.d(TAG, "Cannot open activity for this intent");
        }
    }
}
