package com.sarah.simplenotification.simple_notification;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sarah.simplenotification.R;
import com.sarah.simplenotification.simple_notification.MainActivity;

public class NoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no);

        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(MainActivity.NOTIFICATION_ID);
    }
}
