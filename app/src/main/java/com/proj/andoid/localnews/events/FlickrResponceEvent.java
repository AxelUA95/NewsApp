package com.proj.andoid.localnews.events;

import com.proj.andoid.localnews.model.flickr.Photo;

import java.util.List;

/**
 * created by Alex Ivanov on 11.10.15.
 */
public class FlickrResponceEvent {

    private final List<Photo> model;
    private final boolean hasSearchTypeChanged;

    public FlickrResponceEvent(List<Photo> responseModel, boolean searchTypeChange) {
        model = responseModel;
        hasSearchTypeChanged = searchTypeChange;
    }

    public List<Photo> getModel() {
        return model;
    }

    public boolean hasSearchTypeChanged() {
        return hasSearchTypeChanged;
    }
}
