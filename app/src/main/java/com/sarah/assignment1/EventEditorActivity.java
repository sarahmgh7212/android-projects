package com.sarah.assignment1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashSet;

public class EventEditorActivity extends AppCompatActivity {
    int noteId;
   private EventsClass eventsClass;
    EditText txtEventTitle,txtStartDate,txtEventTime,txtEndDate,txtVenue,
            txtLocation,txtAttendees ;

    Button btnSave;
    DataBaseHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_editor_activity);

        txtEventTitle=(EditText)findViewById(R.id.eventTitle) ;
        txtEventTitle=(EditText)findViewById(R.id.eventTitle);
        txtStartDate=(EditText)findViewById(R.id.startDate);
        txtEventTime=(EditText)findViewById(R.id.eventTime);
        txtEndDate=(EditText)findViewById(R.id.endDate);
        txtVenue=(EditText)findViewById(R.id.venue);
        txtLocation=(EditText)findViewById(R.id.location);
        txtAttendees=(EditText)findViewById(R.id.attendees);

        myDBHelper=new DataBaseHelper(this);


        //***** passsing events class to second activity page that user can edit an event property
         eventsClass = (EventsClass) getIntent().getSerializableExtra("events");

        //editText2.setText(eventsClass.getEventTitle() + " " + eventsClass.getStartDate());

        Intent intent=getIntent();
        noteId=((Intent) intent).getIntExtra("noteId", -1);
        if (noteId !=-1){
            txtEventTitle.setText(MainActivity.events.get(noteId));//saves the event with the title on the first page
            insertEvent();
        }
        else{
            MainActivity.events.add("");
            noteId=MainActivity.events.size() -1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }


        //When editing the event
        txtEventTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        //**** updating the adapter when the user edits the event
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                MainActivity.events.set(noteId,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.sarah.assingment1", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet(MainActivity.events);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //**** Inserting data from texteditor into database
    public void insertEvent(){
        btnSave=(Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted=myDBHelper.addEvent
                        (txtEventTitle.getText().toString(), txtStartDate.getText().toString(),
                                txtEventTime.getText().toString(),
                                txtEndDate.getText().toString(),
                                txtVenue.getText().toString(),
                                txtLocation.getText().toString(),
                                txtAttendees.getText().toString()
                        );

                if(isInserted){
                    Toast.makeText(EventEditorActivity.this,"Data was inserted Successfully!",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(EventEditorActivity.this,"Data insertion Failed!",Toast.LENGTH_LONG).show();

                }
            }
        });



    }
}

