package com.sarah.assignment2_s3667123.fragment;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import com.sarah.assignment2_s3667123.model.Event;
import com.sarah.assignment2_s3667123.model.FileModel;
import com.sarah.assignment2_s3667123.model.FileModelImplement;
import com.sarah.assignment2_s3667123.view.EditListItemActivity;

import java.util.Calendar;
import java.util.Date;
public class EndDatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{
    private final EditListItemActivity myEditListItemActivity;
    private String TAG = getClass().getName();
    private String eventId =null;
    private Context context;
    private String endTimeValue = "";
    private String startDateValue="";
    private String timeValue="";
    private String Contacts = "";
    private String movieId = "";
    private String eventTitle = "";
    private String venue = "";
    private String location = "";

    public EndDatePickerFragment(Context context, EditListItemActivity editListItemActivity, String itemId,String endTimeValue,String startDateValue,String timeValue,
                                 String Contacts,String movieId) {
        this.context = context;
        this.myEditListItemActivity=editListItemActivity;
        this.eventId = itemId;
        this.startDateValue = startDateValue;
        this.timeValue = timeValue;
        this.endTimeValue = endTimeValue;
        this.Contacts = Contacts;
        this.movieId = movieId;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date endDateTime = null;
        final Calendar c = Calendar.getInstance();
        FileModel model = FileModelImplement.getSingletonInstance(context);
        if(eventId!=null) {
            Event event = model.getEventById(eventId);
            endDateTime = event.getEventEndDate();
            c.setTime(endDateTime);
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        eventTitle = myEditListItemActivity.txtEventTitle.getText().toString();
        venue = myEditListItemActivity.txtEventVenue.getText().toString();
        location = myEditListItemActivity.txtEventLocation.getText().toString();
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String endDate= String.valueOf(day)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year);
        Intent editItemIntent = new Intent(context, EditListItemActivity.class);
        editItemIntent.putExtra(Intent.EXTRA_TEXT, eventId);
        editItemIntent.setType("text/plain");
        editItemIntent.putExtra("endDateValue",endDate);
        editItemIntent.putExtra("endTime",endTimeValue);
        editItemIntent.putExtra("timeValue",timeValue);
        editItemIntent.putExtra("startDateValue",startDateValue);
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
