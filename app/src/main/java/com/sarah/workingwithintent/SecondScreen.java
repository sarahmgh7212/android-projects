package com.sarah.workingwithintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SecondScreen extends AppCompatActivity {
    private static final String TAG = "SecondScreen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);
        TextView incomingData = (TextView) findViewById(R.id.etIncomingData);

        Intent incomingIntent=getIntent();
        String incomingName=((Intent) incomingIntent).getStringExtra("name");
        Toast.makeText(SecondScreen.this, "You clicked on:" + incomingName, Toast.LENGTH_LONG).show();
        incomingData.setText(incomingName);
    }
}