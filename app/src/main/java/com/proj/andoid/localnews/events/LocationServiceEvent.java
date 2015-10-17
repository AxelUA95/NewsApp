package com.proj.andoid.localnews.events;

import android.location.Location;

/**
 * created by Alex Ivanov on 10.10.15.
 */
public class LocationServiceEvent {

    private Location location;

    public LocationServiceEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
}
