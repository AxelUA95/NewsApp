package com.proj.andoid.localnews.events;

/**
 * created by Alex Ivanov on 14.10.15.
 */
public class DataSavedEvent {

    public static final int FLICKR_SAVED = 0;

    private final int type;

    public DataSavedEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
