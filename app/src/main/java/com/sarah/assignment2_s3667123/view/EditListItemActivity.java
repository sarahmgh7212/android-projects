package com.sarah.assignment2_s3667123.view;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sarah.assignment2_s3667123.ContactPicker;
import com.sarah.assignment2_s3667123.R;
import com.sarah.assignment2_s3667123.controller.AddMovieButtonListener;
import com.sarah.assignment2_s3667123.controller.EditCancelButtonListener;
import com.sarah.assignment2_s3667123.controller.EditChangeListener;
import com.sarah.assignment2_s3667123.fragment.DatePickerFragment;
import com.sarah.assignment2_s3667123.fragment.EndDatePickerFragment;
import com.sarah.assignment2_s3667123.fragment.EndTimePickerFragment;
import com.sarah.assignment2_s3667123.fragment.TimePickerFragment;
import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;

import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.model.Movie;


public class EditListItemActivity extends AppCompatActivity {
    // The class to show the event details in Create or edit Screen
    protected Button myButton1;
    protected Button cancelButton;
    protected Button addMovie;
    protected Button addTime;
    protected String movieId = null;
    protected Movie movie;
    protected String eventId;
    public TextView tvTitle;
    protected String timeValue=null;
    protected String startDateValue=null;
    protected String endDateValue =null;
    protected String endTime =null;
    protected String Contacts = null;


    public EditText txtEventTitle;
    public EditText txtEventStartDate;
    public EditText txtEventEndDate;
    public EditText txtEventVenue;
    public EditText txtEventLocation;
    public EditText txtEventAttendee;
    public EditText txtEventContact;
    protected String eventTitle = null;
    protected String venue = null;
    protected String location = null;
    private String mContentTitle;

    public boolean flag = false;
    public boolean endFlag = false;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Here the user can edit the event And movie details
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list_item);
        Intent intent = getIntent();
        eventId = (String) intent.getExtras().get(Intent.EXTRA_TEXT);
        mContentTitle = intent.getExtras().getString("FromAddEvent");
        movieId = intent.getExtras().getString("movieId");
        timeValue = intent.getExtras().getString("timeValue");
        startDateValue = intent.getExtras().getString("startDateValue");
        endDateValue = intent.getExtras().getString("endDateValue");
        endTime = intent.getExtras().getString("endTime");
        Contacts = intent.getExtras().getString("Contacts");
        eventTitle = intent.getExtras().getString("eventTitle");
        venue = intent.getExtras().getString("venue");
        location = intent.getExtras().getString("location");


        FileModel fileModel = FileModelImplement.getSingletonInstance(getApplicationContext());

        Event event = fileModel.getEventById(eventId);

        if (movieId != null) {
            movie = fileModel.getMovieById(movieId);
        }

        txtEventTitle = (EditText) findViewById(R.id.mySubItemTextBox);
        txtEventVenue = (EditText) findViewById(R.id.mySubItemTextBox1);
        txtEventLocation = (EditText) findViewById(R.id.mySubItemTextBox2);
        txtEventAttendee = (EditText) findViewById(R.id.mySubItemTextBox3);
        txtEventStartDate = (EditText) findViewById(R.id.mySubItemTextBox4);
        txtEventEndDate = (EditText) findViewById(R.id.mySubItemTextBox5);
        txtEventContact = (EditText) findViewById(R.id.mySubItemTextBox6);
        tvTitle = findViewById(R.id.headerTitle);
        tvTitle.setText(mContentTitle);

        if (eventId != null) {
            if (eventTitle != null) {
                txtEventTitle.setText(eventTitle);
            } else {
                txtEventTitle.setText(event.getEventTiltle());
            }
            if (venue != null) {
                txtEventVenue.setText(venue);
            } else {
                txtEventVenue.setText(event.getEventVenue());
            }
            if (location != null) {
                txtEventLocation.setText(location);
            } else {
                txtEventLocation.setText(event.getEventLocation());
            }
            if (Contacts != null) {
                txtEventContact.setText(Contacts);
            } else if (event.getEventAttendence() == null) {

            } else {
                txtEventContact.setText(event.getEventAttendence().toString());
            }

            if (startDateValue == null) {
                System.out.println("Check1");
                txtEventStartDate.setText(event.getStartDateStr());
                flag = true;
            } else if (startDateValue != null && timeValue != null) {
                System.out.println("Check2");
                txtEventStartDate.setText(startDateValue + " " + timeValue);
            } else {
                System.out.println("Check3");
                txtEventStartDate.setText(startDateValue + " " + "00:00:00 AM");
            }

            if (endDateValue == null) {
                txtEventEndDate.setText(event.getEndDateStr());
                endFlag = true;
            } else if (endDateValue != null && endTime != null) {
                txtEventEndDate.setText(endDateValue + " " + endTime);
            } else {
                txtEventEndDate.setText(endDateValue + " " + "00:00:00 AM");
            }
            if (movieId != null)
                txtEventAttendee.setText(movie.getMovieTitle());
            else if (event.EventGetMovieTitle() != null)
                txtEventAttendee.setText(event.EventGetMovieTitle().getMovieTitle());

        } else {
            if (eventTitle != null) {
                txtEventTitle.setText(eventTitle);
            }
            if (venue != null) {
                txtEventVenue.setText(venue);
            }
            if (location != null) {
                txtEventLocation.setText(location);
            }
        }
        if (eventId == null) {
            if (startDateValue != null && timeValue != null) {
                System.out.println("Check2");
                txtEventStartDate.setText(startDateValue + " " + timeValue);
            } else if (startDateValue != null) {
                txtEventStartDate.setText(startDateValue + " " + "00:00:00 AM");
            }
            if (endDateValue != null && endTime != null) {
                txtEventEndDate.setText(endDateValue + " " + endTime);
            } else if (endDateValue != null) {
                txtEventEndDate.setText(endDateValue + " " + "00:00:00 AM");
            }
        }


        if (movieId != null)
            txtEventAttendee.setText(movie.getMovieTitle());

        if (Contacts != null)
            txtEventContact.setText(Contacts);
        myButton1 = findViewById(R.id.myOkButton);
        try {
            myButton1.setOnClickListener(new EditChangeListener<>(this, this, eventId, movieId));
        } catch (Exception ex) {
            System.err.println("Please don't leave the fields blank" + ex);
        }
        cancelButton = findViewById(R.id.myCancelButton);
        cancelButton.setOnClickListener(new EditCancelButtonListener(this, this));
        addMovie = findViewById(R.id.AddMovie);
        addMovie.setOnClickListener(new AddMovieButtonListener(this, this, eventId, startDateValue, timeValue,
                endDateValue, endDateValue, Contacts));


    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.edit_item_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.add_contact:
                    // Menu for Add Contact
                    Intent i = new Intent(EditListItemActivity.this, ContactPicker.class);
                    i.putExtra("eventId", eventId);
                    i.putExtra("timeValue", timeValue);
                    i.putExtra("startDateValue", startDateValue);
                    i.putExtra("endDateValue", endDateValue);
                    i.putExtra("endTime", endTime);
                    i.putExtra("eventTitle", eventTitle);
                    i.putExtra("venue", venue);
                    i.putExtra("location", location);
                    i.putExtra("movieId", movieId);
                    startActivity(i);
                    return (true);
            }
            return (super.onOptionsItemSelected(item));
    }

    public void showTimePickerDialog(View v) {
        // Call start time picker
        DialogFragment newFragment = new TimePickerFragment(this,this, eventId,startDateValue,endTime,endDateValue,Contacts,movieId);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }



    public void showDatePickerDialog(View v) {
        // Call start Date picker
        DialogFragment newFragment = new DatePickerFragment(this,this, eventId,timeValue,endTime,endDateValue,Contacts,movieId);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showEndDatePickerDialog(View v) {
        // Call End Date picker
        DialogFragment newFragment = new EndDatePickerFragment(this,this, eventId,endTime,startDateValue,timeValue,Contacts,movieId);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showEndTimePickerDialog(View v) {
        // Call End time picker
        DialogFragment newFragment = new EndTimePickerFragment(this,this, eventId,endDateValue,startDateValue,timeValue,Contacts,movieId);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
