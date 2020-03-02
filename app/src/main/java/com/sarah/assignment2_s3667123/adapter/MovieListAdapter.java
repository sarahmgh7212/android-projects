package com.sarah.assignment2_s3667123.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarah.assignment2_s3667123.controller.MovieEditListListener;
import com.sarah.assignment2_s3667123.model.Movie;
import com.sarah.assignment2_s3667123.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class MovieListAdapter extends ArrayAdapter<Movie>{
    // Adapter is created to display the event in tne list.
    private Context context;
    private Map<String, Movie> movies;
    private String eventId = null;
    private String timeValue ="";
    private String startDateValue ="";
    private String endDateValue ="";
    private String endTime ="";
    protected String eventTitle = "";
    protected String venue = "";
    protected String location = "";
    protected String Contacts = "";
    private AssetManager assetManager;
    public MovieListAdapter(Context context, Map<String, Movie> movies,String eventId,String startDateValue,String timeValue,
                            String endDateValue,String endTime,String eventTitle,String venue,String location,String Contacts) {
        // Constructor for Adapter
        super(context, 0, movies.values().toArray(new Movie[movies.size()]));
        this.context = context;
        this.movies = movies;
        this.eventId = eventId;
        this.startDateValue = startDateValue;
        this.timeValue = timeValue;
        this.endDateValue = endDateValue;
        this.endTime = endTime;
        this.eventTitle = eventTitle;
        this.venue = venue;
        this.location = location;
        this.Contacts = Contacts;
    }
    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {
        // Create View for the Movie based on Live view data.
        String movieId = movieId = getItem(position).getMovieId();


        if(listItemView == null) {
            listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item1, parent, false);
        }

        assetManager = context.getAssets();

        TextView myTextView2 = listItemView.findViewById(R.id.myTextView2);
        TextView myTextView3 = listItemView.findViewById(R.id.myTextView3);
        TextView myTextView4 = listItemView.findViewById(R.id.myTextView4);
        ImageView image1 = listItemView.findViewById(R.id.ImageView);
        Button myButton1 = listItemView.findViewById(R.id.AddMovie);
        myButton1.setOnClickListener(new MovieEditListListener(context, movieId,eventId,startDateValue,
                timeValue,endDateValue,endTime,eventTitle,venue,location,Contacts));

        Movie movie = movies.get(movieId);

        myTextView2.setText(movie.getMovieTitle());
        myTextView3.setText(movie.movieYear());
        myTextView4.setText(movie.poster());

        try {

            InputStream ims =  context.getAssets().open(movie.poster());

            Drawable d = Drawable.createFromStream(ims, null);

            image1.setImageDrawable(d);
        }
        catch(IOException ex) {

        }

        return listItemView;
    }
}
