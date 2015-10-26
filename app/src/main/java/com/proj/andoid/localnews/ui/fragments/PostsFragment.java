package com.proj.andoid.localnews.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.proj.andoid.localnews.R;
import com.proj.andoid.localnews.api.TwitterLoader;
import com.proj.andoid.localnews.events.TwitterResponseEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.Subscribe;
import twitter4j.Status;

/**
 * created by Alex Ivanov on 10/18/15.
 */
public class PostsFragment extends BaseFragment {

    @Bind(R.id.tab_recycler_view)
    protected RecyclerView recyclerView;

    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
    private TwitterLoader twitterLoader;

    private int dataType;

    @Override
    protected int getContentResource() {
        return R.layout.tab_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        twitterLoader = new TwitterLoader();
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        bus.unregister(this);
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(TwitterResponseEvent event) {
        dataType = event.getSearchType();
        adapter.addTweets(event.getTweets());
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<PostViewHolder> {

        private List<Status> tweets;

        public RecyclerAdapter() {
            tweets = new ArrayList<>();
        }

        public void addTweets(List<Status> tweetsToAdd) {
            int count = tweets.size();
            tweets.addAll(tweetsToAdd);
            notifyItemRangeInserted(count, tweetsToAdd.size());
        }

        public void deleteAllTweets() {
            int count = tweets.size();
            tweets.clear();
            notifyItemRangeRemoved(0, count);
        }

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PostViewHolder(getActivity().getLayoutInflater()
                    .inflate(R.layout.tweet_item, parent, false));
        }

        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            Status tweet = tweets.get(position);
            holder.tweetTextView.setText(tweet.getText());
        }

        @Override
        public int getItemCount() {
            return tweets.size();
        }
    }

    protected class PostViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tweet_text)
        protected TextView tweetTextView;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
