package com.proj.andoid.nownews.api;

import com.proj.andoid.nownews.model.FlickrResponseModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * created by Alex Ivanov on 08.09.15.
 */
public interface FlickrAPIService {

    String url = "/rest/?method=flickr.photos.search&" +
            "api_key=84855a33b6e038bf5d392f6fdbc4dc50&" +
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
