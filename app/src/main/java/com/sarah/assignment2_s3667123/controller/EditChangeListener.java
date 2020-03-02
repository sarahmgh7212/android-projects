package com.sarah.assignment2_s3667123.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;


import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.EventImpelement;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.model.Movie;
import com.sarah.assignment2_s3667123.view.EditListItemActivity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;


public class EditChangeListener<changeEventTitle, eventItem> extends AppCompatActivity implements View.OnClickListener {
    // Save the Event details from edit and add Event Screen.
    private final EditListItemActivity myEditListItemActivity;
    private String TAG = getClass().getName();
    private String eventId;
    private Context context;
    private String eventTitle;
    private Map<String, Event> eventItem;
    private String eventVenue;
    private String eventLocation;
    private String eventStartDate;
    private String eventEndDate;
    private String eventattendence;


    private Date StartDate;
    private Date EndDate;
    private String movieId = null;
    private String movieTitle;
    private Movie movie;
    private String eventContact = null;
    private int count = 0;
    private ArrayList contactList = new ArrayList();

    public EditChangeListener(Context context,EditListItemActivity editListItemActivity,String eventId,String movieId) {

        this.context = context;
        this.myEditListItemActivity = editListItemActivity;
        this.movieId = movieId;
        this.eventId = eventId;

    }

    @Override
    public void onClick(View v) {
        // saving the input data taken from user
        eventTitle = myEditListItemActivity.txtEventTitle.getText().toString();
        eventVenue = myEditListItemActivity.txtEventVenue.getText().toString();
        eventLocation = myEditListItemActivity.txtEventLocation.getText().toString();
        eventattendence = myEditListItemActivity.txtEventAttendee.getText().toString();
        eventStartDate = myEditListItemActivity.txtEventStartDate.getText().toString();
        eventEndDate = myEditListItemActivity.txtEventEndDate.getText().toString();
        eventContact = myEditListItemActivity.txtEventContact.getText().toString();
        if (eventTitle.isEmpty() || eventVenue.isEmpty() || eventLocation.isEmpty() ||
                eventStartDate.isEmpty() || eventEndDate.isEmpty()) {
            Toast.makeText(context, "Please Fill all the fields", Toast.LENGTH_SHORT).show();

            return;
        } else {

            if (eventContact != null) {
                for (String s : eventContact.split("\n")) {
                    contactList.add(s);
                    count++;
                }
            }

            FileModel model = FileModelImplement.getSingletonInstance(context);
            SQLiteDatabase mDatabase = model.getDBInstance();

            if (mDatabase == null) {
                Log.i(TAG, "Database is empty");

            }

            String sql = "UPDATE events \n" +
                    "SET title = ?, \n" +
                    "startDate = ?, \n" +
                    "endDate = ?, \n" +
                    "venue = ?, \n" +
                    "location = ?, \n" +
                    "movie = ?, \n" +
                    "attendence = ?, \n" +
                    "attendenceCount = ? \n" +
                    "WHERE eventId = ?;\n";
            mDatabase.execSQL(sql, new String[]{eventTitle, eventStartDate, eventEndDate, eventVenue, eventLocation, this.movieId, eventContact, String.valueOf(count), this.eventId});

            Event event = model.getEventById(eventId);
            if (this.movieId != null) {
                movie = model.getMovieById(this.movieId);
            }
            SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy h:mm:ss a");
            String moviename = movie != null ? movie.getMovieTitle() : "";
            eventItem = model.getEventList();
            eventItem.containsKey(eventId);

            Date eventStart = null, eventEnd = null;
            try {

                eventStart = format.parse(eventStartDate);
                eventEnd = format.parse(eventEndDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            eventItem.put(this.eventId, new EventImpelement(
                    this.eventId,
                    eventTitle,
                    eventStartDate,
                    eventEndDate,
                    eventStart,
                    eventEnd,
                    eventVenue,
                    eventLocation,
                    movie,
                    contactList,
                    count
            ));


        }
    }
}
