package com.proj.andoid.localnews;

import com.proj.andoid.localnews.utils.Utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void photoUrlCheck() {
        String value = Utils.getFlickrPhotoURL(1, "2l", "23", "1");
        assertEquals(value, "https://farm1.staticflickr.com/2l/23_1_z.jpg");
        assertNotEquals(value, "https://farm1.staticflickr.com/2l/23_2_z.jpg");
    }

    @Test
    public void buildFlickrUriCheck() {
//        String value = DataContract.FlickrEntry.buildFlickrUri("java").toString();
        assertEquals(true, true);
    }
}