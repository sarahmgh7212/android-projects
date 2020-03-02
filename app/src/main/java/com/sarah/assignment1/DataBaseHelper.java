package com.sarah.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="event.db";
    private final String TABLE_NAME="event_table";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME ,null,1);

    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + TABLE_NAME + "(Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "eventTitle TEXT, startDate TEXT, eventTime TEXT,endDate TEXT,venue TEXT," +
                "location TEXT,attendees TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addEvent(String eventTitle,String startDate, String eventTime,
                            String endDate,String venue,String location,String attendees){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("eventTitle" ,eventTitle);
        contentValues.put("startDate", startDate);
        contentValues.put("eventTime",eventTime);
        contentValues.put("endDate",endDate);
        contentValues.put("venue",venue);
        contentValues.put("location",location);
        contentValues.put("attendees",attendees);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result ==-1){
            return false;


        }
        else {
            return true;
        }
    }

}


