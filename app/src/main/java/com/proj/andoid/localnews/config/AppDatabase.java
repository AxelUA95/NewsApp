package com.proj.andoid.localnews.config;

import android.content.Context;
import android.os.AsyncTask;

import com.proj.andoid.localnews.events.FlickrResponceEvent;
import com.proj.andoid.localnews.model.DbHelper;
import com.proj.andoid.localnews.model.flickr.Photo;
import com.proj.andoid.localnews.utils.Constants;
import com.proj.andoid.localnews.utils.Utils;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * created by Alex Ivanov on 14.10.15.
 */
public class AppDatabase {

    private DbHelper dbHelper;
    private EventBus bus;
    private Context context;

    public AppDatabase(Context c) {
        dbHelper = new DbHelper(c);
        bus = EventBus.getDefault();
        this.context = c;
    }

    public void saveFlickrData(List<Photo> photos) {
        new SaveFlickrData().execute(photos);
    }

    public void loadFlickrData() {
        new LoadFlicrData().execute();
    }

    private class LoadFlicrData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            List<Photo> list = dbHelper.loadFlickrData();
            bus.post(new FlickrResponceEvent(list, Constants.LOCATION_LOAD));
            return null;
        }
    }

    private class SaveFlickrData extends AsyncTask<List<Photo>, Void, Void> {

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Photo>... params) {
            dbHelper.insertFlickrData(params[0]);
            Utils.cleanImageDirectory(context);
            for (Photo imageInfo : params[0]) {
                Utils.savePhoto(context, imageInfo);
            }
            return null;
        }
    }
}
