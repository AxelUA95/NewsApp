package com.proj.andoid.nownews.event;

import com.proj.andoid.nownews.model.FlickrResponseModel;

/**
 * created by Alex Ivanov on 09.09.15.
 */
public class FlickrDataReceiveEvent {

    private FlickrResponseModel flickrResponseModel;

    public FlickrDataReceiveEvent(FlickrResponseModel responseModel) {
        this.flickrResponseModel = responseModel;
    }

    public FlickrResponseModel getFlickrResponseModel() {
        return flickrResponseModel;
    }
}
