package com.proj.andoid.localnews.api;

import com.proj.andoid.localnews.model.ny_times_responce.NYTimesResponce;
import com.proj.andoid.localnews.utils.Constants;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

/**
 * created by Alex Ivanov on 12/20/15.
 */
public class NYTimesLoader {

    private static final long DEFAULT_TIMEOUT = 200L;
    private static long CURRENT_TIMEOUT = DEFAULT_TIMEOUT;

    private String tag = getClass().getName();
    private NYTimesApi apiService;
    private EventBus bus = EventBus.getDefault();

    public NYTimesLoader() {
        resetTimeout();
    }

    public void setApiService(NYTimesApi timesApi) {
        this.apiService = timesApi;
    }

    public void loadByTag(final String tag, final Callback<NYTimesResponce> callBack){
        apiService.getByTag(tag, new Callback<NYTimesResponce>() {
            @Override
            public void success(NYTimesResponce nyTimesResponce, Response response) {
                resetTimeout();
                callBack.success(nyTimesResponce, response);
            }

            @Override
            public void failure(RetrofitError error) {
                increaseTimeout();
                loadByTag(tag, callBack);
            }
        });
    }

    private NYTimesApi setTimeout() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(CURRENT_TIMEOUT, TimeUnit.MILLISECONDS);
        return new RestAdapter.Builder()
                .setEndpoint(Constants.TimesURL)
                .setClient(new OkClient(client))
                .build()
                .create(NYTimesApi.class);
    }

    public void increaseTimeout() {
        CURRENT_TIMEOUT *= 2;
        setApiService(setTimeout());
    }

    public void resetTimeout() {
        CURRENT_TIMEOUT = DEFAULT_TIMEOUT;
        setApiService(setTimeout());
    }

}
