package com.proj.andoid.localnews.events;

import com.proj.andoid.localnews.model.flickr.Photo;

import java.util.List;

/**
 * created by Alex Ivanov on 11.10.15.
 */
public class FlickrResponceEvent {

    private final List<Photo> model;
    private final int searchType;

    public FlickrResponceEvent(List<Photo> responseModel, int searchType) {
        model = responseModel;
        this.searchType = searchType;
    }

    public List<Photo> getModel() {
        return model;
    }

    public int getSearchType() {
        return searchType;
    }
}
