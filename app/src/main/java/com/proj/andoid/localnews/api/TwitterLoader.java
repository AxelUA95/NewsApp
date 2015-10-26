package com.proj.andoid.localnews.api;

import android.os.AsyncTask;
import android.util.Log;

import com.proj.andoid.localnews.events.TwitterResponseEvent;
import com.proj.andoid.localnews.utils.Constants;

import java.util.List;

import de.greenrobot.event.EventBus;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * created by Alex Ivanov on 10/17/15.
 */
public class TwitterLoader {

    private final String tag = getClass().getName();
    private Twitter twitter;
    private EventBus bus;

    public TwitterLoader() {
        buildTwitterConfig();
        bus = EventBus.getDefault();
        new getTweetsAsync().execute("Kyiv");
    }

    private void buildTwitterConfig() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(Constants.TwitterApiKey)
                .setOAuthConsumerSecret(Constants.TwitterApiSecret)
                .setOAuthAccessToken(Constants.TwitterApiAccessToken)
                .setOAuthAccessTokenSecret(Constants.TwitterApiAccessTokenSecret);
        twitter = new TwitterFactory(cb.build()).getInstance();
        Log.i(tag, "twitter client successfully configured");
    }

    private class getTweetsAsync extends AsyncTask<String, Void, List<Status>> {

        @Override
        protected List<twitter4j.Status> doInBackground(String... params) {
            Query query = new Query(params[0]);
            try {
                return twitter.search(query).getTweets();
            } catch (TwitterException e) {
                Log.e(tag, "failed loading tweets", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<twitter4j.Status> statuses) {
            super.onPostExecute(statuses);
            bus.post(new TwitterResponseEvent(statuses, Constants.TAG_LOAD));
        }
    }

}
