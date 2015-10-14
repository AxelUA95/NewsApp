package com.proj.andoid.localnews.ui.fragments;

import android.os.Bundle;
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
import com.proj.andoid.localnews.events.FlickrResponceEvent;
import com.proj.andoid.localnews.model.flickr.Photo;
import com.proj.andoid.localnews.utils.Utils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.Subscribe;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;

/**
 * created by Alex Ivanov on 10.10.15.
 */
public class ImagesFragment extends BaseFragment {

    @Bind(R.id.tab_recycler_view)
    protected RecyclerView recyclerView;

    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
    private FlickrLoader loader;

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
        if (screenSize > 8) {
            columnCount = 5;
        } else if (screenSize > 6) {
            columnCount = 4;
        } else if (screenSize > 5) {
            columnCount = 3;
        } else if (screenSize > 4) {
            columnCount = 2;
        } else {
            columnCount = 1;
        }
        recyclerView.setItemAnimator(new FadeInDownAnimator());
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
        loader.loadByTag("Kiev");
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
    public void onEvent(FlickrResponceEvent event) {
        adapter.addPhotos(event.getModel().getPhotos().getPhoto());
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

        private List<Photo> photos;

        public RecyclerAdapter() {
            photos = new ArrayList<>();
        }

        public void addPhotos(List<Photo> photoList) {
            int count = photos.size();
            photos.addAll(photoList);
            notifyItemRangeInserted(count, photoList.size());
        }

        public void deleteAllPhotos() {
            int count = photos.size();
            photos.clear();
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

            loadPhoto(holder, imageInfo);
        }

        private void loadPhoto(final RecyclerViewHolder holder, final Photo imageInfo) {
            Picasso.with(getActivity())
                    .load(Utils.getFlickrPhotoURL(
                            imageInfo.getFarm(),
                            imageInfo.getServer(),
                            imageInfo.getId(),
                            imageInfo.getSecret()))
                    .into(holder.holderImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.i(tag, "successfully loaded image " + imageInfo.getId());
                            holder.imageProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            Log.e(tag, "Cannot load image, new try");
                            loadPhoto(holder, imageInfo);
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

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
