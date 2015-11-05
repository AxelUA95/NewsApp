package com.proj.andoid.localnews.api;

import android.location.Location;
import android.util.Log;

import com.proj.andoid.localnews.NewsApp;
import com.proj.andoid.localnews.config.AppDatabase;
import com.proj.andoid.localnews.events.FlickrResponceEvent;
import com.proj.andoid.localnews.events.NoInternetConnectionEvent;
import com.proj.andoid.localnews.model.flickr_response.flickrgetphotos.FlickrResponseModel;
import com.proj.andoid.localnews.model.flickr_response.flickrgetphotos.Photo;
import com.proj.andoid.localnews.utils.Constants;

import java.util.List;

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
    private EventBus bus = EventBus.getDefault();

    private int page;
    private int searchType = 1;

    private Location lastLocation;
    private String lastTag = "Kiev";

    public FlickrLoader() {
        apiService = new RestAdapter.Builder()
                .setEndpoint(Constants.FlickrURL)
                .build()
                .create(FlickrAPIService.class);
        page = 0;
        NewsApp.getComponent().inject(this);
    }

    public void loadByLocation(Location position) {
        lastLocation = position;
        if (searchType == Constants.LOCATION_LOAD) {
            page++;
        } else {
            page = 1;
            searchType = Constants.LOCATION_LOAD;
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
        if (searchType == Constants.TAG_LOAD) {
            page++;
        } else {
            page = 1;
            searchType = Constants.TAG_LOAD;
        }
        apiService.getByTag(
                tag,
                "20",
                String.valueOf(page),
                this);
    }

    public void getNextPage() {
        if (searchType == Constants.LOCATION_LOAD) {
            loadByLocation(lastLocation);
        } else {
            loadByTag(lastTag);
        }
    }

    private void postLoadedPhotos(List<Photo> imagesInfo) {
        bus.post(new FlickrResponceEvent(imagesInfo, searchType));
    }

    @Override
    public void success(FlickrResponseModel flickrResponseModel, Response response) {
        if (flickrResponseModel.getStat().equals("ok")) {
            List<Photo> images = flickrResponseModel.getPhotos().getPhoto();
            if (flickrResponseModel.getPhotos().getPage() == 1 &&
                    searchType == Constants.LOCATION_LOAD) {
                database.saveFlickrData(images);
            }
            postLoadedPhotos(images);
        } else {
            Log.e(tag, "error loading Flickr data, check url");
        }
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(tag, "Error loading flickr data ", error);
        bus.post(new NoInternetConnectionEvent());

    }

    public Location getLastLocation() {
        return lastLocation;
    }
}
