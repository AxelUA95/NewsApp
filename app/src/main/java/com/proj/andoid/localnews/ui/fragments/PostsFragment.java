package com.proj.andoid.localnews.ui.fragments;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.proj.andoid.localnews.R;
import com.proj.andoid.localnews.api.TwitterLoader;
import com.proj.andoid.localnews.events.TwitterResponseEvent;
import com.proj.andoid.localnews.utils.Utils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

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
        twitterLoader.loadByTag("");
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
                    .inflate(R.layout.post_item, parent, false));
        }

        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            Status tweet = tweets.get(position);
            Picasso.with(getActivity()).load(tweet.getUser().
                    getBiggerProfileImageURLHttps()).
                    memoryPolicy(MemoryPolicy.NO_CACHE).
                    into(holder.authorImageView);
            holder.authorTextView.setText(tweet.getUser().getName());
            holder.createdAtTextView.setText(tweet.getCreatedAt().toString());
            String postText = tweet.getText();
            holder.postTextView.setText(postText);
            Matcher m = Patterns.WEB_URL.matcher(postText);
            if (m.find()) {
                Picasso.with(getActivity()).load(Uri.parse(m.group())).
                        memoryPolicy(MemoryPolicy.NO_CACHE).
                        into(holder.postImageView);
            }
        }

        @Override
        public int getItemCount() {
            return tweets.size();
        }
    }

    protected class PostViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.author_image_view)
        protected ImageView authorImageView;
        @Bind(R.id.author_text_view)
        protected TextView authorTextView;
        @Bind(R.id.created_at_text_view)
        protected TextView createdAtTextView;
        @Bind(R.id.provider_post_image_view)
        protected ImageView providerImageView;
        @Bind(R.id.post_text_view)
        protected TextView postTextView;
        @Bind(R.id.post_added_image)
        protected ImageView postImageView;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class LoadImage extends AsyncTask<String, Void, Bitmap> {

        private ImageView profileImageView;

        public LoadImage(ImageView imageView) {
            profileImageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                return Picasso.with(getActivity()).load(params[0]).
                        memoryPolicy(MemoryPolicy.NO_CACHE).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            profileImageView.setImageBitmap(Utils.getCircularBitmap(bitmap));
        }
    }
}
