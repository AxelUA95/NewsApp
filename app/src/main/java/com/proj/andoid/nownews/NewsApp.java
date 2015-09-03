package com.proj.andoid.nownews;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * created by Alex Ivanov on 04.09.15.
 */
public class NewsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        //TODO load news from intent service or AsyncTasks (or combine)
    }
}
