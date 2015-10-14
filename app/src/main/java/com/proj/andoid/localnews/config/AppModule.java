package com.proj.andoid.localnews.config;

import android.content.Context;

import com.proj.andoid.localnews.model.DbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * created by Alex Ivanov on 10.10.15.
 */
@Module
public class AppModule {

    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public AppPreferences providePrefs() {
        return new AppPreferences(context);
    }

    @Provides
    @Singleton
    public AppDatabase provideDatabase() {
        return new AppDatabase(context);
    }
}
