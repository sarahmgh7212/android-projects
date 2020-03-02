package com.sarah.assignment2_s3667123.model;

import java.util.ArrayList;
import java.util.Date;

public class MovieImplement extends AbstractMovie {
    // Abstract class to create the Movie
    protected MovieImplement(String movieId, String movieTitle, String movieYear, String poster) {

        super(movieId, movieTitle, movieYear, poster);
    }

    protected MovieImplement(String movieId) {
        super(movieId);
    }

    @Override
    public String getStartDateStr() {
        return null;
    }

    @Override
    public String getEndDateStr() {
        return null;
    }

    @Override
    public void setEventTiltle(String eventTitle) {

    }

    @Override
    public Movie EventGetMovieTitle() {
        return null;
    }

    @Override
    public String getEventId() {
        return null;
    }

    @Override
    public String getEventTiltle() {
        return null;
    }

    @Override
    public Date getEventStartDate() {
        return null;
    }

    @Override
    public Date getEventEndDate() {
        return null;
    }

    @Override
    public String getEventVenue() {
        return null;
    }

    @Override
    public String getEventLocation() {
        return null;
    }

    @Override
    public int getAttendenceCount() {
        return 0;
    }

    @Override
    public ArrayList<String> getEventAttendence() {
        return null;
    }
}


