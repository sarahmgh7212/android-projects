package com.sarah.assignment2_s3667123.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.sarah.assignment2_s3667123.view.EditListItemActivity;
import com.sarah.assignment2_s3667123.view.ListView3Activity;

public class SortAscEventListener implements View.OnClickListener {
    // Ascending sort for Event list.
    private String TAG = getClass().getName();
    private Context context;

    public SortAscEventListener(Context context) {
        //Constructor for Ascending Sort
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        // Naviagate to Event List view after sorting.
        Intent editItemIntent = new Intent(context, ListView3Activity.class);
        editItemIntent.putExtra(Intent.EXTRA_TEXT, "SortAsc");
        editItemIntent.setType("text/plain");
        if (editItemIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(editItemIntent);
        }
    }

}