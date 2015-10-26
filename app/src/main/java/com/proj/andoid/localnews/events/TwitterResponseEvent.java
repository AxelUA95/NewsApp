package com.proj.andoid.localnews.events;

import java.util.List;

import twitter4j.Status;

/**
 * created by Alex Ivanov on 10/19/15.
 */
public class TwitterResponseEvent {

    private List<Status> tweets;
    private int searchType;

    public TwitterResponseEvent(List<Status> tweets, int searchType) {
        this.tweets = tweets;
        this.searchType = searchType;
    }

    public List<Status> getTweets() {
        return tweets;
    }

    public int getSearchType() {
        return searchType;
    }
}
