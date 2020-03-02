package com.sarah.assignment2_s3667123.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.sarah.assignment2_s3667123.view.EditListItemActivity;
import com.sarah.assignment2_s3667123.view.ListView3Activity;
import android.support.v7.app.AppCompatActivity;

public class EditCancelButtonListener extends AppCompatActivity implements View.OnClickListener{
    // Listener to move bact to event list
    private String TAG = getClass().getName();

    private String itemId;
    private Context context;
    public EditCancelButtonListener(Context context,EditListItemActivity editListItemActivity){
        this.context = context;

    }
    @Override
    public void onClick(View v) {
        // Naviagate to Event List
        Intent cancelItemIntent = new Intent(context, ListView3Activity.class);
        cancelItemIntent.setType("text/plain");
        if(cancelItemIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(cancelItemIntent);
        } else {
            Log.i(TAG, "Cannot open activity for this intent");
        }
    }
}
