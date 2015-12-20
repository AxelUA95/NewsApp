package com.proj.andoid.localnews.ui.fragments;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.proj.andoid.localnews.R;
import com.proj.andoid.localnews.api.FlickrLoader;
import com.proj.andoid.localnews.events.FlickrResponseEvent;
import com.proj.andoid.localnews.events.LocationServiceEvent;
import com.proj.andoid.localnews.events.NoInternetConnectionEvent;
import com.proj.andoid.localnews.model.flickr_response.flickrgetphotos.Photo;
import com.proj.andoid.localnews.utils.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.Subscribe;
import jp.wasabeef.recyclerview.animators.BaseItemAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * created by Alex Ivanov on 10.10.15.
 */
public class ImagesFragment extends BaseFragment {

    @Bind(R.id.tab_recycler_view)
    protected RecyclerView recyclerView;

    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
    private FlickrLoader loader;
    private int searchType = 0;
    private boolean useDB = false;

    @Override
    protected int getContentResource() {
        return R.layout.tab_fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter();
        double screenSize = Utils.getScreenSize(getActivity());
        int columnCount;
        if (screenSize > 7) {
            columnCount = 4;
        } else if (screenSize > 5) {
            columnCount = 3;
        } else if (screenSize > 4) {
            columnCount = 2;
        } else {
            columnCount = 1;
        }
        BaseItemAnimator animator = new LandingAnimator();
        animator.setAddDuration(500);
        recyclerView.setItemAnimator(animator);
        layoutManager = new GridLayoutManager(getActivity(), columnCount);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int last = layoutManager.findLastVisibleItemPosition();
                int count = adapter.getItemCount();
                if ((count - 1) == last) {
                    loader.getNextPage();
                }
            }
        });
        loader = new FlickrLoader();
        Location location = loader.getLastLocation();
        if (location != null) {
            loader.loadByLocation(location, true);
        }
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
    public Void onEvent(FlickrResponseEvent event) {
        if (event.getSearchType() != searchType) {
            adapter.deleteAllPhotos();
        }
        adapter.addPhotos(event.getModel());
        searchType = event.getSearchType();
        return null;
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(NoInternetConnectionEvent event) {
        if (adapter.getItemCount() == 0) {
            useDB = true;
            database.loadFlickrData();
        }
        CoordinatorLayout cL =
                (CoordinatorLayout) getActivity().findViewById(R.id.main_coordinator_layout);
        Snackbar.make(cL, R.string.no_internet_connection, Snackbar.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(LocationServiceEvent event) {
        loader.loadByLocation(event.getLocation(), true);
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

        private List<Photo> photos;
        private HashMap<Integer, Bitmap> images;

        public RecyclerAdapter() {
            photos = new ArrayList<>();
            images = new HashMap<>();
        }

        public void addPhotos(List<Photo> photoList) {
            int count = photos.size();
            photos.addAll(photoList);
            notifyItemRangeInserted(count, photoList.size());
        }

        public void deleteAllPhotos() {
            int count = photos.size();
            photos.clear();
            images.clear();
            notifyItemRangeRemoved(0, count);
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RecyclerViewHolder(
                    getActivity().getLayoutInflater()
                            .inflate(R.layout.card_image_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.imageProgressBar.setVisibility(View.VISIBLE);
            Photo imageInfo = photos.get(position);
            if (images.containsKey(position)) {
                holder.holderImageView.setImageBitmap(images.get(position));
                holder.imageProgressBar.setVisibility(View.GONE);
            } else {
                if (useDB) {
                    new LoadImageIntoHolder(images, holder, position).execute(imageInfo.getId());
                } else {
                    loadPhoto(holder, imageInfo, position);
                }
            }
        }

        private void loadPhoto(final RecyclerViewHolder holder, final Photo imageInfo, final int position) {
            Picasso.with(getActivity())
                    .load(Utils.getFlickrPhotoURL(
                            imageInfo.getFarm(),
                            imageInfo.getServer(),
                            imageInfo.getId(),
                            imageInfo.getSecret()))
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(holder.holderImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.i(tag, "Successfully loaded image into holder");
                            holder.imageProgressBar.setVisibility(View.GONE);
                            BitmapDrawable bitmapDrawable = (BitmapDrawable) holder
                                    .holderImageView.getDrawable();
                            images.put(position, bitmapDrawable.getBitmap());
                        }

                        @Override
                        public void onError() {
                            Log.e(tag, "Cannot load image, new try");
                            loadPhoto(holder, imageInfo, position);
                        }
                    });
        }

        @Override
        public int getItemCount() {
            return photos.size();
        }
    }

    protected class RecyclerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.photo_image_view)
        protected ImageView holderImageView;
        @Bind(R.id.image_progress_bar)
        protected ProgressBar imageProgressBar;

        @SuppressWarnings("deprecation")
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageProgressBar.getIndeterminateDrawable().setColorFilter(
                    getResources().getColor(R.color.cyan_700), PorterDuff.Mode.SRC_IN);
        }
    }

    private class LoadImageIntoHolder extends AsyncTask<String, Void, Bitmap> {

        private HashMap<Integer, Bitmap> images;
        private RecyclerViewHolder holder;
        private int position;

        public LoadImageIntoHolder(HashMap<Integer, Bitmap> images, RecyclerViewHolder holder, int pos) {
            this.images = images;
            this.holder = holder;
            this.position = pos;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return Utils.loadPhoto(getActivity(), params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            images.put(position, bitmap);
            holder.holderImageView.setImageBitmap(bitmap);
            holder.imageProgressBar.setVisibility(View.GONE);
        }
    }
}