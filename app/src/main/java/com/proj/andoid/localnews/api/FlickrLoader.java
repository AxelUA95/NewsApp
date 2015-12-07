package com.proj.andoid.localnews.api;

import android.location.Location;
import android.util.Log;

import com.proj.andoid.localnews.NewsApp;
import com.proj.andoid.localnews.config.AppDatabase;
import com.proj.andoid.localnews.events.FlickrResponseEvent;
import com.proj.andoid.localnews.events.NoInternetConnectionEvent;
import com.proj.andoid.localnews.model.flickr_response.flickrgetcomments.FlickrGetComments;
import com.proj.andoid.localnews.model.flickr_response.flickrgetinfo.FlickrGetInfo;
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
    private FlickrAPI apiService;
    private EventBus bus = EventBus.getDefault();

    private int page;
    private int searchType = 1;

    private Location lastLocation;
    private String lastTag = "Kiev";

    public FlickrLoader() {
        apiService = new RestAdapter.Builder()
                .setEndpoint(Constants.FlickrURL)
                .build()
                .create(FlickrAPI.class);
        page = 0;
        NewsApp.getComponent().inject(this);
    }

    public void setApiService(FlickrAPI flickrAPI){
        this.apiService = flickrAPI;
    }

    public void loadByLocation(Location position) {
        lastLocation = position;
        String lng = String.valueOf(position.getLongitude());
        String lat = String.valueOf(position.getLatitude());
        loadByLocation(lng, lat, this);
    }

    public Void loadByLocation(String lng, String lat, Callback<FlickrResponseModel> callback) {
        if (searchType == Constants.LOCATION_LOAD) {
            page++;
        } else {
            page = 1;
            searchType = Constants.LOCATION_LOAD;
        }
        apiService.getByLocation(
                String.valueOf(lat),
                String.valueOf(lng),
                "10",//TODO get this value from prefs
                "km",
                "20",
                String.valueOf(page),
                callback);
        return null;
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

    public void getPhotoInfo(String id, String secret) {
        apiService.getPhotoInfo(id, secret,
                new Callback<FlickrGetInfo>() {
                    @Override
                    public void success(FlickrGetInfo flickrGetInfo, Response response) {
                        //TODO fill it.
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }

    public void getPhotoComments(String id) {
        apiService.getComments(id,
                new Callback<FlickrGetComments>() {
                    @Override
                    public void success(FlickrGetComments flickrGetComments, Response response) {
                        //TODO fill.
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }

    public void postLoadedPhotos(List<Photo> imagesInfo) {
        bus.post(new FlickrResponseEvent(imagesInfo, searchType));
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
            Log.e(tag, "error loading Flickr data, check urlSearch");
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
