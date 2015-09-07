package com.proj.andoid.nownews.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * created by Alex Ivanov on 05.09.15.
 */
public class AppPreferences {

    private final SharedPreferences prefs;

    public AppPreferences(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }
}
