package com.proj.andoid.localnews.utils;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;

import com.proj.andoid.localnews.model.flickr_response.flickrgetphotos.Photo;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * created by Alex Ivanov on 10.10.15.
 */
public class Utils {

    private static final String tag = Utils.class.getName();

    public static String getFlickrPhotoURL(int farmId, String serverId, String id, String secret) {
        return String.format("https://farm%d.staticflickr.com/%s/%s_%s_z.jpg",
                farmId,
                serverId,
                id,
                secret);
    }

    public static double getScreenSize(Context c) {
        DisplayMetrics dm = c.getResources().getDisplayMetrics();
        double density = dm.densityDpi;
        double w = Math.pow(((double) dm.widthPixels / density), 2);
        double h = Math.pow(((double) dm.heightPixels / density), 2);
        return Math.sqrt(w + h);
    }

    private static File getFilePath(Context context, String title) {
        ContextWrapper cw = new ContextWrapper(context);
        File fileDir = cw.getDir(Constants.photoFlikrDir, Context.MODE_PRIVATE);
        return new File(fileDir, title + ".jpg");
    }

    public static void savePhoto(Context context, Photo image) {
        File path = getFilePath(context, image.getId());

        String url = Utils.getFlickrPhotoURL(image.getFarm(),
                image.getServer(), image.getId(), image.getSecret());

        FileOutputStream fos;
        try {
            Bitmap bitmap = Picasso.with(context).load(url).memoryPolicy(MemoryPolicy.NO_STORE).get();
            fos = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
        } catch (IOException e) {
            Log.e(tag, "error saving photo", e);
        }
    }

    public static Bitmap loadPhoto(Context context, String id) {
        File photoFile = getFilePath(context, id);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(photoFile));
        } catch (FileNotFoundException e) {
            Log.e(tag, "Cannot load image from storage ", e);
        }
        return bitmap;
    }

    public static void cleanImageDirectory(Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        File f = cw.getDir(Constants.photoFlikrDir, Context.MODE_PRIVATE);
        deleteRecursive(f);
    }

    private static void deleteRecursive(File f) {
        if (f.isDirectory()) {
            for (File fChild : f.listFiles()) {
                deleteRecursive(fChild);
            }
        }
        if (f.delete()) {
            Log.i(tag, "successfully deleted image");
        }
    }

    public static Bitmap getCircularBitmap(Bitmap bitmap) {
        Bitmap out = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(out);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.BLACK);
        canvas.drawOval(new RectF(rect), paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();

        return out;
    }
}
