package com.proj.andoid.nownews;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.proj.andoid.nownews.config.AppComponent;
import com.proj.andoid.nownews.config.AppModule;
import com.proj.andoid.nownews.config.DaggerAppComponent;
import com.proj.andoid.nownews.utils.ApiLoader;

import io.fabric.sdk.android.Fabric;

/**
 * created by Alex Ivanov on 04.09.15.
 */
public class NewsApp extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        new ApiLoader().loadFlickrByTag(1);
        //TODO load news from intent service or AsyncTasks (or combine)
    }

    public static AppComponent getComponent() {
        return component;
    }
}
