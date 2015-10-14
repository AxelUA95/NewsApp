package com.proj.andoid.localnews.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * created by Alex Ivanov on 10.10.15.
 */
public class Utils {

    public static final String FlickrURL = "https://api.flickr.com/services";
    public static final String FlickrAPIKEY = "3d7619122337835ce1c5c18623d86a4a";

    public static String getFlickrPhotoURL(int farmId, String serverId, String id, String secret) {
        return String.format("https://farm%d.staticflickr.com/%s/%s_%s_b.jpg",
                farmId,
                serverId,
                id,
                secret);
    }

    public static double getScreenSize(Context c) {
        DisplayMetrics dm = c.getResources().getDisplayMetrics();
        double density = dm.densityDpi;
        double w = Math.pow(((double)dm.widthPixels / density) , 2);
        double h = Math.pow(((double)dm.heightPixels / density), 2);
        return  Math.sqrt(w + h);
    }
}
