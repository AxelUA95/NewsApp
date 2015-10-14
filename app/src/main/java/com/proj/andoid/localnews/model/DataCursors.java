package com.proj.andoid.localnews.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.proj.andoid.localnews.model.DataContract.*;
import com.proj.andoid.localnews.model.flickr.Photo;

/**
 * created by Alex Ivanov on 14.10.15.
 */
public interface DataCursors {

    class FlickrCursor extends CursorWrapper {

        /**
         * Creates a cursor wrapper.
         *
         * @param cursor The underlying cursor to wrap.
         */
        public FlickrCursor(Cursor cursor) {
            super(cursor);
        }

        public Photo getFlickrPhoto() {
            if (isBeforeFirst() || isAfterLast()) {
                return null;
            }
            String id = getString(getColumnIndex(FlickrEntry.COLUMN_ID));
            String owner = getString(getColumnIndex(FlickrEntry.COLUMN_OWNER));
            String secret = getString(getColumnIndex(FlickrEntry.COLUMN_SECRET));
            String server = getString(getColumnIndex(FlickrEntry.COLUMN_SERVER));
            int farm = getInt(getColumnIndex(FlickrEntry.COLUMN_FARM));
            String title = getString(getColumnIndex(FlickrEntry.COLUMN_TITLE));
            int isPublic = getInt(getColumnIndex(FlickrEntry.COLUNM_IS_PUBLIC));
            int isFriend = getInt(getColumnIndex(FlickrEntry.COLUMN_IS_FRIEND));
            int isFamily = getInt(getColumnIndex(FlickrEntry.COLUMN_IS_FRIEND));
            return new Photo(id, owner,secret,server, farm, title, isPublic, isFriend, isFamily);
        }

    }
}
