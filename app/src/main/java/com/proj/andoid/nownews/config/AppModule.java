package com.proj.andoid.nownews.config;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * created by Alex Ivanov on 05.09.15.
 */
@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides@Singleton
    public AppPreferences providePrefs() {
        return new AppPreferences(context);
    }

}
