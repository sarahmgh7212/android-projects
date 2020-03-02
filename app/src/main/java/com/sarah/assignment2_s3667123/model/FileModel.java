package com.sarah.assignment2_s3667123.model;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

public interface FileModel {
// interface to create movie and event object.
    Map<String, Event> getEventList();

    Event getEventById(String eventId);

    Event getEventByTitle(String title);

    Map<String, Movie>  getMovieList();

    Movie getMovieById(String movieId);

    SQLiteDatabase getDBInstance();

    public String EventList();

}
