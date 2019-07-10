package com.example.fcmpushnotification;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.fcmpushnotification.R;
import com.example.fcmpushnotification.helper.NotificationHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessageService extends FirebaseMessagingService {

    public MyFirebaseMessageService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size()>0) {
            Log.d("FIREBASETOKEN", "hello uganda "+remoteMessage.getData());
        }

        Log.d("FIREBASETOKEN", "hello uganda "+remoteMessage.getData().size());

        //checking if , there is any notification
        if (remoteMessage.getNotification()!=null) {
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();
            NotificationHelper helper = new NotificationHelper();
            helper.setBody(message); helper.setTitle(title);
            NotificationHelper.displayNotification(getApplicationContext(), helper);
            Log.d("FIREBASETOKEN", "notification is not empty");

        }else {
         //Toast.makeText( , "notification is empty" , Toast.LENGTH_LONG).show();
            Log.d("FIREBASETOKEN", "notification is empty");
        }


    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.d("FIREBASETOKEN", "onNewToken: "+s);
    }
}
