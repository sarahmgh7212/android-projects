package com.sarah.simplenotification.reminding_notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sarah.simplenotification.R;
//https://www.youtube.com/watch?v=1fV9NmvxXJo
import java.util.Calendar;

public class Notification2 extends AppCompatActivity {
    private Button btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification2_activity);

         btn=(Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY,23);

                calendar.set(Calendar.MINUTE, 55);

                Intent intent=new Intent(getApplicationContext(),NotificationReceiver.class);

                PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);

            }
        });

        // initNotif();
    }
//    public void initNotif(){
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar=Calendar.getInstance();
//
//                calendar.set(Calendar.HOUR_OF_DAY,23);
//
//                calendar.set(Calendar.MINUTE, 55);
//
//                Intent intent=new Intent(getApplicationContext(),NotificationReceiver.class);
//
//                PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//
//                AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
//                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//
//            }
//        });
//
//    }

}
