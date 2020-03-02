package com.sarah.assignment2_s3667123.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sarah.assignment2_s3667123.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.

                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the notification
        NotificationScheduler.showNotification(context, MainActivity.class,
                "You have 3 unwatched videos", "Want to Watch them now?");

    }
}


