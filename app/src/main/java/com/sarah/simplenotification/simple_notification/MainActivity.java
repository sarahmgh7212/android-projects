package com.sarah.simplenotification.simple_notification;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sarah.simplenotification.R;

//https://www.youtube.com/watch?v=uuypb1Up7Wo&list=PLshdtb5UWjSrOJfpFOE-u55s3SnY2EO9v&index=47
//https://www.youtube.com/watch?v=tTbd1Mfi-Sk&list=PLrnPJCHvNZuDR7-cBjRXssxYK0Y5EEKzr&index=2
public class MainActivity extends AppCompatActivity {


    private final String CHANNEL_ID = "personal_notification";
   public static final int NOTIFICATION_ID = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayNotification(View view) {
        // createNotificationChannel();

        //Switching to Landing Activity page by clicking the notfication
        Intent intent = new Intent(this, LandingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // Switching to Yes Activity by clicking the YES button
        Intent yesIntent=new Intent(this, YesActivity.class);
        yesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent yesPendingIntent =PendingIntent.getActivity(this,0,yesIntent,PendingIntent.FLAG_ONE_SHOT);

        // Switching to No Activity by clicking the NO button
        Intent noIntent=new Intent(this, NoActivity.class);
        noIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent noPendingIntent =PendingIntent.getActivity(this,0,noIntent,PendingIntent.FLAG_ONE_SHOT);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_error_notification);
        builder.setContentTitle("Reminding Notification");
        builder.setContentText("This is a simple notification..");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.addAction(R.drawable.ic_error_notification,"Yes",yesPendingIntent);
        builder.addAction(R.drawable.ic_error_notification,"No",noPendingIntent);

        //To go to the activity when the notification is clicked:
        builder.setContentIntent(pendingIntent);

        //To clear the notification after it jumps to the LandingActivity
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());

    }
}
    //In order to show the notification properly for android ver 8.0 and above
//    private void createNotificationChannel(){
//        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
//            CharSequence name= "Personal Notification";
//            String description= "Include all the personal notification";
//            int importance= NotificationManager.IMPORTANCE_DEFAULT;
//
//            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,name,importance);
//
//            notificationChannel.setDescription(description);
//
//            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        }
//    }
//}
