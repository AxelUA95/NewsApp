package com.proj.andoid.localnews;

import com.proj.andoid.localnews.api.TwitterLoader;

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
import twitter4j.Status;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * created by nadtsaty on 12/8/15.
 */
public class TwitterLoaderTests {

    @Mock
    public TwitterLoader twitterLoader;
    @Captor
    public ArgumentCaptor<Callback<List<Status>>> response;

    private List<Status> tweets = new ArrayList<>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadTweetsByTagTest() {
        twitterLoader.loadByTag("Kyiv", new Callback<List<Status>>() {
            @Override
            public void success(List<Status> statuses, Response response) {
                tweets.addAll(statuses);
            }

            @Override
            public void failure(RetrofitError error) {
                tweets.clear();
            }
        });

        verify(twitterLoader).loadByTag(anyString(), response.capture());

        List<Status> mock = createStatus();

        response.getValue().success(mock, null);

        assertTrue(tweets.size() == 10);
    }

    @Test
    public void loadTweetsByTagBadRequest() {
        twitterLoader.loadByTag("Kyiv", new Callback<List<Status>>() {
            @Override
            public void success(List<Status> statuses, Response response) {
                tweets.addAll(statuses);
            }

            @Override
            public void failure(RetrofitError error) {
                tweets.clear();
            }
        });

        verify(twitterLoader).loadByTag(anyString(), response.capture());

        response.getValue().failure(null);

        assertTrue(tweets.size() == 0);
    }

    private List<Status> createStatus() {
        Status s = mock(Status.class);
        ArrayList<Status> statuses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            statuses.add(s);
        }
        return statuses;
    }
}
