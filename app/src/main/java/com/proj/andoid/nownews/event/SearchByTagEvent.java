package com.proj.andoid.nownews.event;

/**
 * created by Alex Ivanov on 03.10.15.
 */
public class SearchByTagEvent {

    private final String tag;

    public SearchByTagEvent(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
