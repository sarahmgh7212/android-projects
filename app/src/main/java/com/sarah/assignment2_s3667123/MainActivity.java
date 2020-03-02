package com.sarah.assignment2_s3667123;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.sarah.assignment2_s3667123.google_map.MapActivity;
import com.sarah.assignment2_s3667123.internet_checking.NetworkChangeReceiver;
import com.sarah.assignment2_s3667123.notification.AlarmReceiver;
import com.sarah.assignment2_s3667123.notification.LocalData;
import com.sarah.assignment2_s3667123.notification.NotificationScheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String TAG = "RemindMe";
    LocalData localData;

    SwitchCompat reminderSwitch;
    TextView tvTime;

    LinearLayout ll_set_time;

    int hour, min;
    int GPS_SETTINGS = 1000;

    ClipboardManager myClipboard;

    private BroadcastReceiver mNetworkReceiver;
    static TextView tv_check_connection;

    GoogleApiClient client;
    LocationRequest mLocationRequest;
    PendingResult<LocationSettingsResult> result;

    Button mClickBtn;


    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        client = new GoogleApiClient.Builder(this)
                //.addApi(AppIndex.API)
                .addApi(LocationServices.API)
                .build();


        mClickBtn = findViewById(R.id.btn_selection);
        mClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForGPS();
            }
        });

        tv_check_connection = findViewById(R.id.tv_check_connection);
        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();

        localData = new LocalData(getApplicationContext());

        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        ll_set_time = findViewById(R.id.ll_set_time);

        tvTime = findViewById(R.id.tv_reminder_time_desc);

        reminderSwitch = findViewById(R.id.timerSwitch);

        hour = localData.get_hour();
        min = localData.get_min();

        tvTime.setText(getFormatedTime(hour, min));
        reminderSwitch.setChecked(localData.getReminderStatus());

        if (!localData.getReminderStatus())
            ll_set_time.setAlpha(0.4f);

        reminderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                localData.setReminderStatus(isChecked);
                if (isChecked) {
                    Log.d(TAG, "onCheckedChanged: true");
                    NotificationScheduler.setReminder(MainActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                    ll_set_time.setAlpha(1f);
                } else {
                    Log.d(TAG, "onCheckedChanged: false");
                    NotificationScheduler.cancelNotifReminder(MainActivity.this, AlarmReceiver.class);
                    ll_set_time.setAlpha(0.4f);
                }

            }
        });

        ll_set_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (localData.getReminderStatus())
                    showTimePickerDialog(localData.get_hour(), localData.get_min());
            }
        });

    }


    private void showTimePickerDialog(int h, int m) {

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.timepicker_header, null);

        TimePickerDialog builder = new TimePickerDialog(this, R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        Log.d(TAG, "onTimeSet: hour " + hour);
                        Log.d(TAG, "onTimeSet: min " + min);
                        localData.set_hour(hour);
                        localData.set_min(min);
                        tvTime.setText(getFormatedTime(hour, min));
                        NotificationScheduler.setReminder(MainActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());


                    }
                }, h, m, false);

        builder.setCustomTitle(view);
        builder.show();

    }

    public String getFormatedTime(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        client.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        client.disconnect();
    }

    public static void dialog(boolean value) {

        if (value) {
            tv_check_connection.setText("We are back !!!");
            tv_check_connection.setBackgroundColor(Color.GREEN);
            tv_check_connection.setTextColor(Color.WHITE);

            Handler handler = new Handler();
            Runnable delayrunnable = new Runnable() {
                @Override
                public void run() {
                    tv_check_connection.setVisibility(View.GONE);
                }
            };
            handler.postDelayed(delayrunnable, 3000);
        } else {
            tv_check_connection.setVisibility(View.VISIBLE);
            tv_check_connection.setText("Could not Connect to internet");
            tv_check_connection.setBackgroundColor(Color.RED);
            tv_check_connection.setTextColor(Color.WHITE);
        }
    }


    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void askForGPS(){
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        result = LocationServices.SettingsApi.checkLocationSettings(client, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Intent i = new Intent(MainActivity.this, MapActivity.class);
                        startActivity(i);
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(MainActivity.this, GPS_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}

