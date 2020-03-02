package com.sarah.assignment2_s3667123.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.sarah.assignment2_s3667123.view.EditListItemActivity;
import com.sarah.assignment2_s3667123.view.MovieListViewActivity;
import android.support.v7.app.AppCompatActivity;

public class AddMovieButtonListener extends AppCompatActivity implements View.OnClickListener{
    // Listener to accept onclick to naviagate to movie list.
    private final EditListItemActivity myEditListItemActivity;
    private String TAG = getClass().getName();
    private String eventId;
    private Context context;
    private String timeValue = "";
    private String endTimeValue = "";
    private String endDateValue = "";
    private String startDateValue="";
    protected String eventTitle = "";
    protected String venue = "";
    protected String location = "";
    protected String Contacts = "";

    public AddMovieButtonListener(Context context,EditListItemActivity editListItemActivity,String itemId,String startDateValue,
                                  String timeValue,String endDateValue,String endTimeValue, String Contacts) {
        //Constructor for AddMovie
        this.context = context;
        this.myEditListItemActivity=editListItemActivity;
        this.eventId = itemId;
        this.startDateValue = startDateValue;
        this.timeValue = timeValue;
        this.endDateValue = endDateValue;
        this.endTimeValue = endTimeValue;
        this.Contacts = Contacts;
    }

    @Override
    public void onClick(View v) {
        // Display the Movie list.
                Intent editItemIntent = new Intent(context, MovieListViewActivity.class);
                eventTitle = myEditListItemActivity.txtEventTitle.getText().toString();
                venue = myEditListItemActivity.txtEventVenue.getText().toString();
                location = myEditListItemActivity.txtEventLocation.getText().toString();
                editItemIntent.putExtra(Intent.EXTRA_TEXT, eventId);
                editItemIntent.setType("text/plain");
                editItemIntent.putExtra("endDateValue",endDateValue);
                editItemIntent.putExtra("endTime",endTimeValue);
                editItemIntent.putExtra("timeValue",timeValue);
                editItemIntent.putExtra("startDateValue",startDateValue);
                editItemIntent.putExtra("eventTitle",eventTitle);
                editItemIntent.putExtra("venue",venue);
                editItemIntent.putExtra("location",location);
                editItemIntent.putExtra("eventTitle",eventTitle);
                editItemIntent.putExtra("venue",venue);
                editItemIntent.putExtra("location",location);
                editItemIntent.putExtra("Contacts",Contacts);

                if(editItemIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(editItemIntent);
                } else {
                    Log.i(TAG, "Cannot open activity for this intent");
                }
    }
}
