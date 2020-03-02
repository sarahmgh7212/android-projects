package com.sarah.assignment2_s3667123.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.view.EditListItemActivity;

import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    private final EditListItemActivity myEditListItemActivity;
    private String TAG = getClass().getName();
    private String eventId =null;
    private Context context;
    private String startDateValue = "";
    private String endTimeValue = "";
    private String endDateValue = "";
    private String Contacts = "";
    private String movieId = "";
    private String eventTitle = "";
    private String venue = "";
    private String location = "";

    public TimePickerFragment(Context context, EditListItemActivity editListItemActivity, String itemId,String startDateValue,String endTimeValue,
                              String endDateValue,String Contacts,String movieId){
    this.context = context;
    this.myEditListItemActivity=editListItemActivity;
    this.eventId = itemId;
    this.startDateValue = startDateValue;
    this.endDateValue = endDateValue;
    this.endTimeValue = endTimeValue;
    this.Contacts = Contacts;
    this.movieId = movieId;

}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date startTime = null;
        final Calendar c = Calendar.getInstance();
        FileModel model = FileModelImplement.getSingletonInstance(context);
        if(eventId!=null) {
            Event event = model.getEventById(eventId);
            startTime = event.getEventStartDate();
            c.setTime(startTime);
        }
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int amOrPm = c.get(Calendar.AM_PM);
        eventTitle = myEditListItemActivity.txtEventTitle.getText().toString();
        venue = myEditListItemActivity.txtEventVenue.getText().toString();
        location = myEditListItemActivity.txtEventLocation.getText().toString();

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time;
        if(12>hourOfDay) {
            time = String.valueOf(hourOfDay) + ":" + String.valueOf(minute)+":00"+" AM";
        }else{
            time = String.valueOf(hourOfDay) + ":" + String.valueOf(minute)+":00"+" PM";
        }
        Intent editItemIntent = new Intent(context, EditListItemActivity.class);
        editItemIntent.putExtra(Intent.EXTRA_TEXT, eventId);
        editItemIntent.setType("text/plain");
        editItemIntent.putExtra("timeValue",time);
        editItemIntent.putExtra("startDateValue",startDateValue);
        editItemIntent.putExtra("endDateValue",endDateValue);
        editItemIntent.putExtra("endTime",endTimeValue);
        editItemIntent.putExtra("eventTitle",eventTitle);
        editItemIntent.putExtra("venue",venue);
        editItemIntent.putExtra("location",location);
        editItemIntent.putExtra("movieId",movieId);
        editItemIntent.putExtra("Contacts",Contacts);

        if(editItemIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(editItemIntent);
        } else {
            Log.i(TAG, "Cannot open activity for this intent");
        }
    }
}
