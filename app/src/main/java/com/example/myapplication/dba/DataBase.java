package com.example.myapplication.dba;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class DataBase extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
