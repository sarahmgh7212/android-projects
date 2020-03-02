package com.sarah.assignment2_s3667123.model;

import java.util.ArrayList;
import java.util.Date;

public interface Event {
    // Interface to Event

    String getEventId();

    String getEventTiltle();

    Date getEventStartDate();

    Date getEventEndDate();
    String getEventVenue();
    String getEventLocation();
    int getAttendenceCount();
    ArrayList<String> getEventAttendence();

    String getStartDateStr();

//    void setStartDateStr();

    String getEndDateStr() ;

//    void setEndDateStr();

    void setEventTiltle(String eventTitle);

    Movie EventGetMovieTitle();
}
