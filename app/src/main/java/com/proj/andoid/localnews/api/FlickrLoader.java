package com.proj.andoid.localnews.api;

import android.location.Location;
import android.util.Log;

import com.proj.andoid.localnews.NewsApp;
import com.proj.andoid.localnews.config.AppDatabase;
import com.proj.andoid.localnews.events.FlickrResponceEvent;
import com.proj.andoid.localnews.model.flickr.FlickrResponseModel;
import com.proj.andoid.localnews.utils.Utils;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * created by Alex Ivanov on 11.10.15.
 */
public class FlickrLoader implements Callback<FlickrResponseModel> {

    @Inject
    protected AppDatabase database;

    private String tag = getClass().getName();
    private FlickrAPIService apiService;

    private int page;
    private boolean loadByLocation;
    private boolean hasSearchTypeChanged = false;

    private Location lastLocation;
    private String lastTag = "Kiev";

    public FlickrLoader() {
        apiService = new RestAdapter.Builder()
                .setEndpoint(Utils.FlickrURL)
                .build()
                .create(FlickrAPIService.class);
        page = 0;
        NewsApp.getComponent().inject(this);
    }

    public void loadByLocation(Location position) {
        lastLocation = position;
        if (loadByLocation) {
            page++;
            hasSearchTypeChanged = false;
        } else {
            loadByLocation = true;
            page = 1;
            hasSearchTypeChanged = true;
        }
        apiService.getByLocation(
                String.valueOf(position.getLatitude()),
                String.valueOf(position.getLongitude()),
                "10",//TODO get this value from prefs
                "km",
                "20",
                String.valueOf(page),
                this);
    }

    public void loadByTag(String tag) {
        lastTag = tag;

        if (!loadByLocation) {
            page++;
            hasSearchTypeChanged = false;
        } else {
            loadByLocation = false;
            page = 1;
            hasSearchTypeChanged = true;
        }
        apiService.getByTag(
                tag,
                "20",
                String.valueOf(page),
                this);
    }

    public void getNextPage() {
        if (loadByLocation) {
            loadByLocation(lastLocation);
        } else {
            loadByTag(lastTag);
        }
    }

    @Override
    public void success(FlickrResponseModel flickrResponseModel, Response response) {
        if (flickrResponseModel.getStat().equals("ok")) {
            if (flickrResponseModel.getPhotos().getPage() == 1 && !loadByLocation) {
                database.saveFlickrData(flickrResponseModel.getPhotos().getPhoto());
            } else {
                EventBus.getDefault().post(
                        new FlickrResponceEvent(flickrResponseModel.getPhotos().getPhoto(),
                                hasSearchTypeChanged));
            }
        } else {
            Log.e(tag, "error loading Flickr data, check url");
        }
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(tag, "Error loading flickr data ", error);
    }
}
