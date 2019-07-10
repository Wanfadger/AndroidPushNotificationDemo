package com.example.fcmpushnotification.helper;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.fcmpushnotification.R;

public class NotificationHelper {

    private String title , body;
    private  static final String CHANNEL_ID = "22";
    private static int NOTIFICATION_ID = 32;


    public static void displayNotification(Context context , NotificationHelper helper){

        //builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context , CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle(helper.getTitle());
        builder.setContentText(helper.getBody());
        builder.setVibrate(new long[]{1000 ,1000 , 1000});

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(NOTIFICATION_ID , builder.build());


    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
