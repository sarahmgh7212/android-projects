package com.sarah.assignment1;

import java.io.Serializable;

public class EventsClass implements Serializable {
    private String eventTitle;
    private  String startDate;
    private String eventTime;
    private String endDate;
    private String venue;
    private String location;
    private String attendees;

    //Building the contstructor
    public EventsClass(String eventTitle,String startDate,String eventTime,String endDate,
                       String venue,String location,String attendees){
        this.eventTitle=eventTitle;
        this.startDate=startDate;
        this.eventTime=eventTime;
        this.endDate=endDate;
        this.venue=venue;
        this.location=location;
         this.attendees= attendees;

    }
 //**********Implementing getters and setters**********
    public String getEventTitle() {
        return this.eventTitle;
    }

    public void setEventTitle(String  eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String  startDate) {
        this.startDate = startDate;
    }


    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        venue = venue;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

}
