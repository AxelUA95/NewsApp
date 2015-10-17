package com.proj.andoid.localnews.events;

/**
 * created by Alex Ivanov on 15.10.15.
 */
public class LoadByTagEvent {

    private String tag;

    public LoadByTagEvent(String tag) {
        this.tag = tag.trim();
    }

    public String getTag() {
        return tag;
    }
}
