package com.sarah.alarmcreator;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

//https://stacktips.com/tutorials/android/repeat-alarm-example-in-android
public class MainActivity extends AppCompatActivity {

    Button btnStart,btnStop,btnStartAgain;
    EditText editText;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.editText);
        btnStart=(Button)findViewById(R.id.btnStart);
        btnStop=(Button)findViewById(R.id.btnStop);
        btnStartAgain=(Button)findViewById(R.id.btnStartAgain);

        Intent alarmIntent = new Intent(MainActivity.this, AlarmBroadcastReceiver.class);
        pendingIntent =
                PendingIntent.getBroadcast(this.getApplicationContext(),23432,alarmIntent,0);

    }

    public void  stopAlarm(View view){

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);



        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAlert(View view){

        int i=Integer.parseInt(editText.getText().toString());
        Intent intent=new Intent(this, AlarmBroadcastReceiver.class);


        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
        +(i+1000),pendingIntent);

        Toast.makeText(this, "Alarm set in" + i+ "seconds", Toast.LENGTH_LONG).show();

    }

//    public void startAgainAlarm(View view){
//        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        int interval = 1000 * 60 * 20;
//
//        /* Set the alarm to start at 10:30 AM */
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.set(Calendar.HOUR_OF_DAY, 20);
//        calendar.set(Calendar.MINUTE, 55);
//        Toast.makeText(this, "Alarm Started again", Toast.LENGTH_SHORT).show();
//
//        /* Repeating on every 20 minutes interval */
//        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                1000 * 60 * 1, pendingIntent);
//        Log.i("making sure the alarm is repeated", "Alarm is repeated");
//    }
}
