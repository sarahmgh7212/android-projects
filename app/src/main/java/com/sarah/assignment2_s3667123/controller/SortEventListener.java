package com.sarah.assignment2_s3667123.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.sarah.assignment2_s3667123.view.EditListItemActivity;
import com.sarah.assignment2_s3667123.view.ListView3Activity;
// Descending sort of Events.
public class SortEventListener implements View.OnClickListener{

    private String TAG = getClass().getName();
    private Context context;

    public SortEventListener(Context context) {

        this.context = context;
    }
    @Override
    public void onClick(View v) {
        // Go to to Event List view after sorting.
        Intent intent = new Intent(context, ListView3Activity.class);
        intent.putExtra(Intent.EXTRA_TEXT, "SortDesc");
        intent.setType("text/plain");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}