package com.proj.andoid.localnews.api;

import com.proj.andoid.localnews.model.flickr_response.flickrgetphotos.FlickrResponseModel;
import com.proj.andoid.localnews.utils.Constants;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * created by Alex Ivanov on 08.09.15.
 */
public interface FlickrAPIService {

    String url = "/rest/?method=flickr.photos.search&" +
            "api_key=" + Constants.FlickrAPIKEY + "&" +
            "format=json&" +
            "nojsoncallback=1&" +
            "privacy_filter=1&" +
            "media=photos&" +
            "in_gallery=true";

    @GET(url)
    void getByTag(@Query("text") String tag,
                  @Query("per_page") String perPage,
                  @Query("page") String page,
                  Callback<FlickrResponseModel> callback);

    @GET(url)
    void getByLocation(@Query("lat") String latitude,
                       @Query("lon") String longitude,
                       @Query("radius") String radius,
                       @Query("radius_units") String radUnits,
                       @Query("per_page") String perPage,
                       @Query("page") String page,
                       Callback<FlickrResponseModel> callback);

}
