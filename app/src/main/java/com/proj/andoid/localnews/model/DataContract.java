package com.proj.andoid.localnews.model;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * created by Alex Ivanov on 12.10.15.
 */
public class DataContract {

    public static final String CONTENT_AUTHORITY = "com.proj.android.localnews";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_FLICKR = "flickr";

    public static final class FlickrEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FLICKR).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FLICKR;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FLICKR;

        public static final String TABLE_NAME = "flickr";

        public static final String COLUMN_ID = "flickr_id";
        public static final String COLUMN_OWNER = "owner";
        public static final String COLUMN_SECRET = "secret";
        public static final String COLUMN_SERVER = "server";
        public static final String COLUMN_FARM = "farm";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUNM_IS_PUBLIC = "is_public";
        public static final String COLUMN_IS_FRIEND = "is_friend";
        public static final String COLUMN_IS_FAMILY = "is_family";

        public static Uri buildFLickrUri(String id) {
            return CONTENT_URI.buildUpon().appendEncodedPath(id).build();
        }
    }
}
