package com.proj.andoid.localnews.config;

import android.content.Context;
import android.os.AsyncTask;

import com.proj.andoid.localnews.events.FlickrResponceEvent;
import com.proj.andoid.localnews.model.DbHelper;
import com.proj.andoid.localnews.model.flickr.Photo;

import java.util.List;

/**
 * created by Alex Ivanov on 14.10.15.
 */
public class AppDatabase {

    private DbHelper dbHelper;

    public AppDatabase(Context c) {
        dbHelper = new DbHelper(c);
    }

    private class LoadFlicrData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            new FlickrResponceEvent(dbHelper.loadFlickrData(), true);
            return null;
        }
    }

    private class SaveFlickrData extends AsyncTask<List<Photo>, Void, Void> {

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Photo>... params) {
            dbHelper.insertFlickrData(params[0]);
            //TODO new event to show new data is ready
            return null;
        }
    }
}
