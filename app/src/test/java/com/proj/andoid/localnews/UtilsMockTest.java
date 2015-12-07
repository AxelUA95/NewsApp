package com.proj.andoid.localnews;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.proj.andoid.localnews.utils.Constants;
import com.proj.andoid.localnews.utils.Utils;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * created by Alex Ivanov on 12/6/15.
 */
public class UtilsMockTest {

    @Test
    public void testScreenSize() {
        Context c = mock(Context.class);
        Resources res = mock(Resources.class);
        DisplayMetrics dm = mock(DisplayMetrics.class);
        when(c.getResources()).thenReturn(res);
        when(res.getDisplayMetrics()).thenReturn(dm);
        dm.densityDpi = 3;
        dm.widthPixels = 1080;
        dm.heightPixels = 1920;

        assertTrue(Utils.getScreenSize(c) == Math.sqrt(1296 + 4096) * 10);
        verify(c).getResources();
        verify(res).getDisplayMetrics();
    }
    @Test
    public void testFilePath() {
        String name = "name";
        String flickrDir = Constants.photoFlikrDir;
        ContextWrapper cw = mock(ContextWrapper.class);
        File f = new File(flickrDir);
        when(cw.getDir(flickrDir, Context.MODE_PRIVATE)).thenReturn(f);
        assertTrue(new File(f + "/" + name + ".jpg").equals(Utils.getFilePath(cw, name)));
        verify(cw).getDir(flickrDir, Context.MODE_PRIVATE);
    }

    @Test
    public void testFilePathSecond() {
        String name = "";
        String flickDir = "dir";
        ContextWrapper cw = mock(ContextWrapper.class);
        File f = new File(name);
        when(cw.getDir(flickDir, Context.MODE_PRIVATE)).thenReturn(f);
        assertNotEquals(new File(flickDir + "/" + name + ".jpg"), Utils.getFilePath(cw, name));
        verify(cw).getDir(Constants.photoFlikrDir, Context.MODE_PRIVATE);
    }

    @Test
    public void testFilePathNull() {
        String name = "hello";
        ContextWrapper cw = mock(ContextWrapper.class);
        try {
            Utils.getFilePath(cw, name);
        } catch (Exception e) {
            verify(cw.getDir(Constants.photoFlikrDir, Context.MODE_PRIVATE));
            assertTrue(e instanceof NullPointerException);
        }
    }
}
