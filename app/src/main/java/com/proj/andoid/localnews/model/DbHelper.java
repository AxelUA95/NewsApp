package com.proj.andoid.localnews.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.proj.andoid.localnews.model.flickr.Photo;

import java.util.ArrayList;
import java.util.List;

import static com.proj.andoid.localnews.model.DataContract.FlickrEntry;

/**
 * created by Alex Ivanov on 12.10.15.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String[] allFlickrColumns = {FlickrEntry.COLUMN_ID,
            FlickrEntry.COLUMN_OWNER, FlickrEntry.COLUMN_SECRET, FlickrEntry.COLUMN_SERVER,
            FlickrEntry.COLUMN_FARM, FlickrEntry.COLUMN_TITLE, FlickrEntry.COLUNM_IS_PUBLIC,
            FlickrEntry.COLUMN_IS_FRIEND, FlickrEntry.COLUMN_IS_FAMILY};

    static final String DATABASE_NAME = "localnews.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FLICKR_TABLE = "CREATE TABLE " + FlickrEntry.TABLE_NAME + " (" +
                FlickrEntry.COLUMN_ID + " TEXT PRIMARY KEY, " +
                FlickrEntry.COLUMN_OWNER + " TEXT, " +
                FlickrEntry.COLUMN_SECRET + " TEXT NOT NULL, " +
                FlickrEntry.COLUMN_SERVER + " TEXT NOT NULL, " +
                FlickrEntry.COLUMN_FARM + " INTEGER NOT NULL, " +
                FlickrEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                FlickrEntry.COLUNM_IS_PUBLIC + " INTEGER, " +
                FlickrEntry.COLUMN_IS_FRIEND + " INTEGER, " +
                FlickrEntry.COLUMN_IS_FAMILY + " INTEGER " + " );";

        db.execSQL(SQL_CREATE_FLICKR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FlickrEntry.TABLE_NAME);
        onCreate(db);
    }

    public void insertFlickrData(List<Photo> photos) {
        List<ContentValues> cvList = new ArrayList<>();
        for (Photo imageData : photos) {
            cvList.add(getContentValues(imageData));
        }
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            database.delete(FlickrEntry.TABLE_NAME, null, null);
            for (ContentValues cv : cvList) {
                database.insert(FlickrEntry.TABLE_NAME, null, cv);
            }
        } finally {
            database.endTransaction();
        }
    }

    public List<Photo> loadFlickrData() {
        List<Photo> photos = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().query(FlickrEntry.TABLE_NAME, allFlickrColumns,
                    null, null, null, null, null);
            DataCursors.FlickrCursor flickrCursor = new DataCursors.FlickrCursor(cursor);
            flickrCursor.moveToFirst();
            while (!flickrCursor.isAfterLast()) {
                photos.add(flickrCursor.getFlickrPhoto());
                flickrCursor.moveToNext();
            }
        } catch (Exception e) {
            Log.e("FlickrDB", "Cannot load flickr data from database", e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return photos;
    }

    private ContentValues getContentValues(Photo imageData) {
        ContentValues cv = new ContentValues();
        cv.put(FlickrEntry.COLUMN_ID, imageData.getId());
        cv.put(FlickrEntry.COLUMN_OWNER, imageData.getOwner());
        cv.put(FlickrEntry.COLUMN_SECRET, imageData.getSecret());
        cv.put(FlickrEntry.COLUMN_SERVER, imageData.getServer());
        cv.put(FlickrEntry.COLUMN_FARM, imageData.getFarm());
        cv.put(FlickrEntry.COLUMN_TITLE, imageData.getTitle());
        cv.put(FlickrEntry.COLUNM_IS_PUBLIC, imageData.getIspublic());
        cv.put(FlickrEntry.COLUMN_IS_FRIEND, imageData.getIsfriend());
        cv.put(FlickrEntry.COLUMN_IS_FAMILY, imageData.getIsfamily());
        return cv;
    }
}
