package com.example.alice.androidchat;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by andrearodriguez on 6/7/16.
 */
public class AndroidChatApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
