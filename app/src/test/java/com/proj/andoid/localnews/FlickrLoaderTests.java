package com.proj.andoid.localnews;

import com.proj.andoid.localnews.api.FlickrLoader;
import com.proj.andoid.localnews.model.flickr_response.flickrgetphotos.FlickrResponseModel;
import com.proj.andoid.localnews.model.flickr_response.flickrgetphotos.Photo;
import com.proj.andoid.localnews.model.flickr_response.flickrgetphotos.Photos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * created by nastia on 07.12.15.
 */
public class FlickrLoaderTests {

    @Mock
    public FlickrLoader flickrLoader;
    @Captor
    public ArgumentCaptor<Callback<FlickrResponseModel>> response;

    private List<Photo> images = new ArrayList<>();
    private int page = -1;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadByLocationTest() {
        flickrLoader.loadByLocation("123", "21", new Callback<FlickrResponseModel>() {
            @Override
            public void success(FlickrResponseModel flickrResponseModel, Response response) {
                images.addAll(flickrResponseModel.getPhotos().getPhoto());
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
        verify(flickrLoader).loadByLocation(anyString(), anyString(), response.capture());

        FlickrResponseModel model = createFlickrResponseModel();

        response.getValue().success(model, null);
        assertTrue(images.size() == 10);
    }

    @Test
    public void loadByLocationBadResponse() {
        flickrLoader.loadByLocation("123", "21", new Callback<FlickrResponseModel>() {
            @Override
            public void success(FlickrResponseModel flickrResponseModel, Response response) {
                images.addAll(flickrResponseModel.getPhotos().getPhoto());
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
        verify(flickrLoader).loadByLocation(anyString(), anyString(), response.capture());

        response.getValue().failure(null);
        assertTrue(images.size() == 0);
    }

    private FlickrResponseModel createFlickrResponseModel() {
        FlickrResponseModel model = new FlickrResponseModel();
        model.setStat("OK");
        Photos photo = new Photos();
        model.setPhotos(photo);
        photo.setPage(1);
        photo.setPages("20");
        photo.setPerpage(10);
        photo.setTotal("200");
        Photo img = new Photo("sajkhd,", "adha", "asdjkha", "adjkh", 1, "hello", 0, 0, 0);
        List<Photo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(img);
        }
        photo.setPhoto(list);
        return model;
    }

    @Test
    public void loadByTag() {
        flickrLoader.loadByTag("tag", new Callback<FlickrResponseModel>() {
            @Override
            public void success(FlickrResponseModel flickrResponseModel, Response response) {
                images = flickrResponseModel.getPhotos().getPhoto();
                page = flickrResponseModel.getPhotos().getPage();
            }

            @Override
            public void failure(RetrofitError error) {
                images.clear();
            }
        });
        verify(flickrLoader).loadByTag(anyString(), response.capture());
        FlickrResponseModel model = createFlickrResponseModel();

        response.getValue().success(model, null);
        assertTrue(images.size() == 10);
        assertTrue(page == 1);
    }

    @Test
    public void loadByTagWithBadRequest() {
        flickrLoader.loadByTag("tag", new Callback<FlickrResponseModel>() {
            @Override
            public void success(FlickrResponseModel flickrResponseModel, Response response) {
                images = flickrResponseModel.getPhotos().getPhoto();
                page = flickrResponseModel.getPhotos().getPage();
            }

            @Override
            public void failure(RetrofitError error) {
                images.clear();
            }
        });
        verify(flickrLoader).loadByTag(anyString(), response.capture());

        response.getValue().failure(null);
        assertTrue(images.size() == 0);
        assertTrue(page == -1);
    }
}
