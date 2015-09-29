package com.proj.andoid.nownews.event;

import android.location.Location;

/**
 * created by Polieskov Anton on 29.09.15.
 */
public class SearchByLocationEvent {

    private Location location;

    private SearchByLocationEvent(){}

    public SearchByLocationEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
