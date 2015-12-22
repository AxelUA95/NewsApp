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
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * created by Alex Ivanov on 11.10.15.
 */
public class FlickrLoader implements Callback<FlickrResponseModel> {

    private static final long DEFAULT_TIMEOUT = 200L;
    private static long CURRENT_TIMEOUT = DEFAULT_TIMEOUT;

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
        resetTimeout();
        page = 0;
        NewsApp.getComponent().inject(this);
    }

    public void setApiService(FlickrAPI flickrAPI) {
        this.apiService = flickrAPI;
    }

    public void loadByLocation(Location position, boolean nextPage) {
        lastLocation = position;
        String lng = String.valueOf(position.getLongitude());
        String lat = String.valueOf(position.getLatitude());
        loadByLocation(lng, lat, this, nextPage);
    }

    public void loadByLocation(final String lng, final String lat, final Callback<FlickrResponseModel> callback, boolean nextPage) {
        if (nextPage) {
            if (searchType == Constants.LOCATION_LOAD) {
                page++;
            } else {
                page = 1;
                searchType = Constants.LOCATION_LOAD;
            }
        }

        apiService.getByLocation(
                String.valueOf(lat),
                String.valueOf(lng),
                "10",//TODO get this value from prefs
                "km",
                "20",
                String.valueOf(page),
                callback);
    }

    private FlickrAPI setTimeout() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(CURRENT_TIMEOUT, TimeUnit.MILLISECONDS);
        return new RestAdapter.Builder()
                .setEndpoint(Constants.FlickrURL)
                .setClient(new OkClient(client))
                .build()
                .create(FlickrAPI.class);
    }

    public void increaseTimeout() {
        CURRENT_TIMEOUT *= 2;
        setApiService(setTimeout());
    }

    public void resetTimeout() {
        CURRENT_TIMEOUT = DEFAULT_TIMEOUT;
        setApiService(setTimeout());
    }

    public void loadByTag(final String tag, final Callback<FlickrResponseModel> callback, boolean nextPage) {
        lastTag = tag;
        if (nextPage) {
            if (searchType == Constants.TAG_LOAD) {
                page++;
            } else {
                page = 1;
                searchType = Constants.TAG_LOAD;
            }
        }
        apiService.getByTag(
                tag,
                "20",
                String.valueOf(page),
                callback);
    }

    public void loadByTag(String tag, boolean nextPage) {
        loadByTag(tag, this, nextPage);
    }

    public void getNextPage() {
        if (searchType == Constants.LOCATION_LOAD) {
            loadByLocation(lastLocation, true);
        } else {
            loadByTag(lastTag, true);
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
        if (CURRENT_TIMEOUT < 600000) {
            increaseTimeout();
            if (lastLocation != null) {
                loadByLocation(lastLocation, false);
            } else {
                loadByTag(lastTag, false);
            }
        } else {
            resetTimeout();
            bus.post(new NoInternetConnectionEvent());
        }

    }

    public Location getLastLocation() {
        return lastLocation;
    }
}
