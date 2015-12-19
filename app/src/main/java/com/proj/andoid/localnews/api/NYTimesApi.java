package com.proj.andoid.localnews.api;

import com.proj.andoid.localnews.model.ny_times_responce.NYTimesResponce;
import com.proj.andoid.localnews.utils.Constants;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * created by Alex Ivanov on 12/20/15.
 */
public interface NYTimesApi {

    String url = "/svc/search/v2/articlesearch.json" +
            "?api-key=" + Constants.NYTimesSearchApiKey;

    @GET(url)
    void getByTag(@Query("q") String tag, Callback<NYTimesResponce> callback);

}
