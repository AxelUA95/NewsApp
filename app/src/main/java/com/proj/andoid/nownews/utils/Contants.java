package com.proj.andoid.nownews.utils;

/**
 * created by Alex Ivanov on 08.09.15.
 */
public class Contants {

    public static String FlickrURL = "https://api.flickr.com/services";
    public static String FlickrAPIKEY = "ce4bbd1ec4ea4811f82b48558f2fa0fb";

    public static String getFlickrPhotoURL(int farmId, String serverId, String id, String secret) {
        return String.format("https://farm%d.staticflickr.com/%s/%s_%s_c.jpg",
                farmId,
                serverId,
                id,
                secret);
    }
}
