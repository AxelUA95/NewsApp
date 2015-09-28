package com.proj.andoid.nownews.utils;

import android.util.Log;

import com.proj.andoid.nownews.api.FlickrAPIService;
import com.proj.andoid.nownews.event.FlickrDataReceiveEvent;
import com.proj.andoid.nownews.model.FlickrResponseModel;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * created by Alex Ivanov on 08.09.15.
 */
public class ApiLoader {

    private final String tag = getClass().getName();
    private FlickrAPIService flickrAPIService;

    public ApiLoader() {
        flickrAPIService = new RestAdapter.Builder()
                .setEndpoint(Contants.FlickrURL)
                .build()
                .create(FlickrAPIService.class);
    }

    public void loadFlickrByTag(int page) {
        flickrAPIService.getByTag("kiev", "10", String.valueOf(page), new Callback<FlickrResponseModel>() {
            @Override
            public void success(FlickrResponseModel flickrResponseModel, Response response) {
                if (flickrResponseModel.getStat().equals("ok")) {
                    EventBus.getDefault().post(new FlickrDataReceiveEvent(flickrResponseModel));
                } else {
                    Log.e(tag, "error loading Flickr data, check url");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(tag, "error loading FlickrData", error);
            }
        });
    }

}
