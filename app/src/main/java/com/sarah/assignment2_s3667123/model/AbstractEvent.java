package com.sarah.assignment2_s3667123.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractEvent implements Event {
// Create Abstract Event class
    private String eventId;
    private String eventTitle;
    private String startDateStr;
    private String endDateStr;
    private Date startDate;
    private Date endDate;
    private String venue;
    private String location;
    private Movie movie;
    private ArrayList<String> attendence=new ArrayList<String>();
    private int attendenceCount;

//    "CREATE TABLE IF NOT EXISTS events (\n" +
//            "    eventid varchar(200) NOT NULL CONSTRAINT employees_pk PRIMARY KEY ,\n" +
//            "    title varchar(200) NOT NULL,\n" +
//            "    startdate datetime NOT NULL,\n" +
//            "    endDate datetime NOT NULL,\n" +
//            "    venue varchar(200) NOT NULL,\n" +
//            "    location varchar(200) NOT NULL,\n" +
//            "    movie varchar(200) NOT NULL,\n" +
//            "    FOREIGN KEY(movie) REFERENCES movies(movieid),\n" +
//            "    attendence varchar(500) NOT NULL,\n" +
//            "    attendenceCount integer NOT NULL\n" +
//            ");"

    protected AbstractEvent(String eventId, String eventTitle,String startDateStr,String endDateStr, Date startDate, Date endDate, String venue,
                            String location, Movie movie, ArrayList<String> attendence, int attendenceCount) {
        //Constructor for Event Class
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.startDateStr = startDateStr;
        this.endDateStr = endDateStr;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.location = location;
        this.attendence = attendence;
        this.attendenceCount = attendenceCount;
        this.movie = movie;
    }

    protected AbstractEvent(String eventId, String eventTitle, Date startDate, Date endDate, String venue,
                            String location,int attendenceCount) {
        //Constructor for Event Class
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.location = location;
        this.attendence = null;
    }

    protected AbstractEvent(String eventId, String eventTitle, Date startDate, Date endDate, String venue,
                            String location,Movie movie,int attendenceCount,ArrayList<String>attendence) {
        //Constructor for Event Class
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.venue = venue;
        this.location = location;
        this.movie = movie;
        this.attendenceCount = attendenceCount;
        this.attendence = attendence;
    }
    protected AbstractEvent(String eventId,String eventTitle,
                            String location) {
        //Constructor for Event Class
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.location = location;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventTiltle() {
        return eventTitle;
    }

    public Date getEventStartDate() {
        return startDate;
    }
    public Date getEventEndDate() {
        return endDate;
    }
    public String getEventVenue() {
        return venue;
    }
    public String getEventLocation() {
        return location;
    }

    public ArrayList<String> getEventAttendence(){
        return attendence;
    }

    @Override
    public Movie EventGetMovieTitle(){
        return movie;
    }

    @Override
    public void setEventTiltle(String eventTitle){
        this.eventTitle = eventTitle;
    }

    public int getAttendenceCount(){
        return attendenceCount;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    @Override
    public String toString() {
        return String.format("Event Title = %s,+'\n'+startDate = ", eventTitle,startDate);
    }
}
