package com.sarah.assignment2_s3667123.internet_checking;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static com.sarah.assignment2_s3667123.MainActivity.dialog;


public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (isOnline(context)) {
                dialog(true);
                Log.d("Internet", "Internet is now connected ");
            } else {
                dialog(false);
                Log.d("Internet", "Conectivity Failure !!! ");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (networkInfo != null && networkInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

}