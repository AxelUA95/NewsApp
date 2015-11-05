package com.proj.andoid.localnews.model.flickr_response.flickrgetphotos;

import com.google.gson.annotations.Expose;

/**
 * created by Alex Ivanov on 08.09.15.
 */
public class FlickrResponseModel {

    @Expose
    private Photos photos;
    @Expose
    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public String getStat() {
        return stat;
    }
}
