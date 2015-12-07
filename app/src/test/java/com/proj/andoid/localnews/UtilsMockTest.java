package com.proj.andoid.localnews;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.proj.andoid.localnews.utils.Utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
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
}
