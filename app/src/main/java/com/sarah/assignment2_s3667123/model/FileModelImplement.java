package com.sarah.assignment2_s3667123.model;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import com.sarah.assignment2_s3667123.R;

import java.text.SimpleDateFormat;
import java.util.UUID;

import static java.lang.System.*;

public class FileModelImplement implements FileModel {
// Class to read the file data and update to the respective class

    private static final Object DateTimeFormat = "";
    private final String TAG = getClass().getName();

    private Map<String, Event> events = new HashMap<>();

    private Map<String, Movie> movies = new HashMap<>();

    String eventListItem="";
    public SQLiteDatabase mDatabase =applicationContext.openOrCreateDatabase(DATABASE_NAME, applicationContext.MODE_PRIVATE, null);
    private static Context applicationContext;
    private DateTimeFormatter DateTime;
    public static final String DATABASE_NAME = "MoviePlannerDatabase";

    //mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);


    @RequiresApi(api = Build.VERSION_CODES.O)
    private FileModelImplement() {
        loadItems();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private static class LazyHolder {
        static final FileModel INSTANCE = new FileModelImplement();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static FileModel getSingletonInstance(Context appContext) {
        // it will return the Event and Movie object
        if(applicationContext == null) {
           applicationContext = appContext;
        }
        return LazyHolder.INSTANCE;
    }
    public Map<String, Event> getEventList() {
        return events;
    }

    @Override
    public Event getEventById(String eventId) {
        return events.get(eventId);
    }

    @Override
    public Event getEventByTitle(String title)     {
        // Method to return the movie title
        for(Event ev:events.values())
        {
            if(ev.getEventTiltle().compareToIgnoreCase(title)==0)
            {
                return ev;
            }
        }
        return null;
    }

    public void changeTitle(String eventId,String eventTitle){
        // Method to return the change Event title value from event
        for(Event ev:events.values())
        {
            if((ev.getEventId().compareToIgnoreCase(eventId)==0) &&ev.getEventTiltle().compareToIgnoreCase(eventTitle)==0){
                continue;
            }else{
                ev.setEventTiltle(eventTitle);
            }
        }
    }

    public String EventList(){
        // Return the event lists
        for(Event ev:events.values()){
            if(ev.hashCode()!=0){
                eventListItem += ev.toString() +'\n';
            }
        }
        return eventListItem;
    }

    public Map<String, Movie> getMovieList() {
        return movies;
    }
    @Override
    public Movie getMovieById(String movieId) {
        return movies.get(movieId);
    }

    @Override
    public SQLiteDatabase getDBInstance() {
        return this.mDatabase;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadItems() {
        // load the event and movie value from the file
        Scanner scanner = null;
        Scanner sc = null;
        if (events.isEmpty()) {
            events.clear();
            movies.clear();

            try {
                sc = new Scanner(applicationContext.getResources().openRawResource(R.raw.movies));
                while (sc.hasNext()) {
                    String line = sc.nextLine();

                    if (line.contains("//")) {
                        continue;
                    }
                    line = line.replaceAll("\"", "");
                    String[] components = line.split(",");
                    UUID movieUUid = UUID.randomUUID();
                    String movieId = movieUUid.toString();
//                    movies.put(movieId, (Movie) new MovieImplement(movieId, components[1], components[2], components[3]));
                    String insertSQL = "INSERT INTO movies \n" +
                            "(movieId, movietitle, year, poster)\n" +
                            "VALUES \n" +
                            "(?, ?, ?, ?);";
                    /*mDatabase.execSQL(insertSQL, new String[]{evendId, components[1], components[2], components[3],components[4],components[5],null,null, String.valueOf(0)});
                    showEmployeesFromDatabase();*/
                    mDatabase.execSQL(insertSQL, new String[]{movieId, components[1], components[2], components[3]});

                }
            } catch (Exception e) {
                Log.i(TAG, e.getMessage());
            } finally {
                if (sc != null) {
                    sc.close();
                }
            }

            try {
                showMoviesFromDatabase();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                scanner = new Scanner(applicationContext.getResources().openRawResource(R.raw.events));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy h:mm:ss a");
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line.contains("//")) {
                        continue;
                    }
                    line = line.replaceAll("\"", "");
                    String[] components = line.split(",");
                    UUID eventUUid = UUID.randomUUID();
                    String evendId = eventUUid.toString();
                    String insertSQL = "INSERT INTO events \n" +
                            "(eventId, title, startDate, endDate,venue, location, movie, attendence,attendenceCount)\n" +
                            "VALUES \n" +
                            "(?, ?, ?, ?,?,?,?,?,?);";
                    /*mDatabase.execSQL(insertSQL, new String[]{evendId, components[1], components[2], components[3],components[4],components[5],null,null, String.valueOf(0)});
                    showEmployeesFromDatabase();*/
                    mDatabase.execSQL(insertSQL, new String[]{evendId, components[1], components[2], components[3], components[4], components[5]+","+components[6],null,null,String.valueOf(0)});
//                    events.put(evendId, new EventImpelement(evendId, components[1], format.parse(components[2]), format.parse(components[3]), components[4], components[5]+","+components[6],null,null,0));
                }
            } catch (Exception e) {
                Log.i(TAG, e.getMessage());
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
            try {
                showEventsFromDatabase();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void showEventsFromDatabase() throws ParseException {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy h:mm:ss a");
        Cursor cursorEvent = mDatabase.rawQuery("SELECT * FROM events", null);
        Date start=null,end=null;
        //if the cursor has some data
        if (cursorEvent.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                out.println("cursorEvent111"+cursorEvent.getString(0));
                start=format.parse(cursorEvent.getString(2));
                end=format.parse(cursorEvent.getString(3));
                Log.i(TAG, "-------------------------------------------------");
                Log.i(TAG, "Start date        " + cursorEvent.getString(2));
                Log.i(TAG, "end date          " + cursorEvent.getString(3));
                Log.i(TAG, "Start date        " + start.toString());
                Log.i(TAG, "end date          " + end.toString());
                Log.i(TAG, "--------------------------------------------------");
                events.put(cursorEvent.getString(0),new EventImpelement(
                        cursorEvent.getString(0),
                        cursorEvent.getString(1),
                        cursorEvent.getString(2),
                        cursorEvent.getString(3),
                        start,
                        end,
                        cursorEvent.getString(4),
                        cursorEvent.getString(5),
                        null,
                        null,
                        cursorEvent.getInt(7)
                ));
            } while (cursorEvent.moveToNext());
        }
    }

    private void showMoviesFromDatabase() throws ParseException {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        SimpleDateFormat format = new SimpleDateFormat("d/MM/yyyy h:mm:ss a");
        if(mDatabase == null) {
            Log.i(TAG, "Database is empty");
        }
        Cursor cursorMovie = mDatabase.rawQuery("SELECT * FROM movies", null);

        //if the cursor has some data
        if (cursorMovie.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                out.println("cursorMovie111"+cursorMovie.getString(0));
                movies.put(cursorMovie.getString(0),new MovieImplement(
                        cursorMovie.getString(0),
                        cursorMovie.getString(1),
                        cursorMovie.getString(2),
                        cursorMovie.getString(3))
                );
            } while (cursorMovie.moveToNext());
        }
    }
}
