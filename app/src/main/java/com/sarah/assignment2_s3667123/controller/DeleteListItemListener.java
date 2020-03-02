package com.sarah.assignment2_s3667123.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.view.ListView3Activity;

import java.util.Map;

public class DeleteListItemListener extends AppCompatActivity implements View.OnClickListener{
    // Listener to Delete the event from event list
    private String TAG = getClass().getName();

    private String itemId;
    private Context context;
    private Map<String, Event> events;

    public DeleteListItemListener(Context context, String itemId) {
        // Constructor to Delete Event
        this.context = context;
        this.itemId = itemId;
    }

    @Override
    public void onClick(View v) {
        // Navigate to main Screen from delete the event.
        FileModel model = FileModelImplement.getSingletonInstance(context);
        Event event = model.getEventById(itemId);
        events = model.getEventList();
        events.remove(itemId);
        Intent editItemIntent = new Intent(context, ListView3Activity.class);
        editItemIntent.putExtra(Intent.EXTRA_TEXT, itemId);
        editItemIntent.setType("text/plain");

        if(editItemIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(editItemIntent);
        } else {
            Log.i(TAG, "Cannot open activity for this intent");
        }
    }
}

