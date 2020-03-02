package com.sarah.alarmcreator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Time to Get up", Toast.LENGTH_LONG).show();

        Vibrator vibrator=(Vibrator)context.getSystemService(
                Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }
}
