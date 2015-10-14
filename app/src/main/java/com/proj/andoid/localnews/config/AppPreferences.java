package com.proj.andoid.localnews.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * created by Alex Ivanov on 10.10.15.
 */
public class AppPreferences {

    private final SharedPreferences prefs;

    public AppPreferences(Context c) {
        prefs = PreferenceManager.getDefaultSharedPreferences(c);
    }
}
