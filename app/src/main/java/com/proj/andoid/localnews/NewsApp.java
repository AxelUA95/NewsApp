package com.proj.andoid.localnews;

import android.support.multidex.MultiDexApplication;

import com.proj.andoid.localnews.config.AppComponent;
import com.proj.andoid.localnews.config.AppModule;
import com.proj.andoid.localnews.config.DaggerAppComponent;

/**
 * created by Alex Ivanov on 10.10.15.
 */
public class NewsApp extends MultiDexApplication {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}
