package com.example.fcmpushnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fcmpushnotification.helper.NotificationHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        ///GETTING DEVICE FCM TOKEN ON CLICK
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()) {
                    final TextView textView = findViewById(R.id.text1);
                    String token  = task.getResult().getToken();
                    textView.setText(token);
                    Log.d("FIREBASETOKEN", "onCreate: "+ token);
                }else{
                    Toast.makeText(getApplicationContext() , task.getException().getMessage() , Toast.LENGTH_LONG).show();
                    Log.d("FIREBASETOKEN", "onCreate: "+ task.getException().getMessage());
                }
            }
        });

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CHANNEL_ONE";
            String description = "This channel is meant for testing channel usage";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            final String CHANNEL_ID = "22";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
