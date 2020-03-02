package com.sarah.assignment2_s3667123.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.sarah.assignment2_s3667123.view.MovieListViewActivity;

public class MovieEditChangeListener<changeEventTitle, itemId> implements View.OnClickListener {
    // Listener to naviagate to Movie List
    private String TAG = getClass().getName();

    private String movieId;
    private Context context;

    public MovieEditChangeListener(Context context, String movieId) {
        //Constructor to naviagate to movie List Activity.
        this.context = context;
        this.movieId = movieId;
    }
    @Override
    public void onClick(View v) {
        // Naviagate to Movie List Activity.
        Intent editItemIntent = new Intent(context, MovieListViewActivity.class);
        editItemIntent.putExtra(Intent.EXTRA_TEXT, movieId);
        editItemIntent.setType("text/plain");

        if(editItemIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(editItemIntent);
        } else {
            Log.i(TAG, "Cannot open activity for this intent");
        }
    }
}

