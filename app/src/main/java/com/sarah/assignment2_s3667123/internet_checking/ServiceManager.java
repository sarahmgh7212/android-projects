package com.sarah.assignment2_s3667123.internet_checking;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ServiceManager {
    Context context;

    public ServiceManager(Context base) {
        context = base;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
