package com.sarah.assignment2_s3667123.model;

import java.util.ArrayList;
import java.util.Date;

public class EventImpelement extends AbstractEvent {
    // Event Implementation class to call Event Class

    public EventImpelement(String EventId, String EventTitle, Date startDate, Date endDate, String venue,
                           String location, Movie movie, int noAttendees, ArrayList contact) {
        // Event Implementation class to call Movie Class
        super(EventId,EventTitle,startDate,endDate,venue,location,movie,noAttendees,contact);
    }

    public EventImpelement(String EventId, String EventTitle, Date startDate, Date endDate, String venue,
                           String location, int noAttendees) {
        super(EventId,EventTitle,startDate,endDate,venue,location,noAttendees);
    }


    public EventImpelement(String EventId, String EventTitle, String startDateStr, String endDateStr, Date startDate, Date endDate, String venue,
                           String location, Movie movie, ArrayList<String> attendence, int noAttendees) {
        super(EventId,EventTitle,startDateStr,endDateStr,startDate,endDate,venue,location,movie,attendence,noAttendees);
    }

    public String test(){
        return null;
    }

}

