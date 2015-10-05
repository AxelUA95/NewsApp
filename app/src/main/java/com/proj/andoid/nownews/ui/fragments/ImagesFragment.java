package com.proj.andoid.nownews.ui.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.proj.andoid.nownews.R;
import com.proj.andoid.nownews.event.FlickrDataReceiveEvent;
import com.proj.andoid.nownews.event.SearchByLocationEvent;
import com.proj.andoid.nownews.event.SearchByTagEvent;
import com.proj.andoid.nownews.model.FlickrResponseModel;
import com.proj.andoid.nownews.utils.ApiLoader;
import com.proj.andoid.nownews.utils.Contants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.Subscribe;

/**
 * created by Alex Ivanov on 07.09.15.
 */
public class ImagesFragment extends BaseFragment {

    @Bind(R.id.tab_recycler_view)
    protected RecyclerView imageRecycler;
    private ImageAdapter imageAdapter;
    private LinearLayoutManager layoutManager;
    private int page;
    private Location lastLocation;
    private ApiLoader loader;


    @Override
    protected int getContentView() {
        return R.layout.tab_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loader = new ApiLoader();
        imageRecycler.setHasFixedSize(true);
        imageAdapter = new ImageAdapter();
        imageRecycler.setItemAnimator(new DefaultItemAnimator());
        layoutManager = new LinearLayoutManager(getActivity());
        imageRecycler.setLayoutManager(layoutManager);
        imageRecycler.setAdapter(imageAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        BUS.register(this);
        page = 1;
        imageRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisible = layoutManager.findLastVisibleItemPosition();
                int count = layoutManager.getItemCount() - 1;
                if (count == lastVisible && count != 0) {
                    page++;
                    searchLocation();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        BUS.unregister(this);
    }

    private void searchLocation() {
        if (lastLocation != null) {
            loader.loadFlickrByLocation(lastLocation.getLatitude(),
                    lastLocation.getLongitude(), page);
        }
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(FlickrDataReceiveEvent event) {
        if (page == 1 && imageAdapter.getItemCount() != 0) {
            imageAdapter.deleteAllItems();
        }
        imageAdapter.addPhotoItems(event.getFlickrResponseModel().getPhotos().getPhoto());
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(SearchByLocationEvent event) {
        page = 1;
        lastLocation = event.getLocation();
        searchLocation();
    }

    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(SearchByTagEvent event) {
        page = 1;
        loader.loadFlickrByTag(event.getTag(), page);
    }

    private class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {

        private List<FlickrResponseModel.Photo> photos;

        public ImageAdapter() {
            photos = new ArrayList<>();
        }

        public void addPhotoItems(List<FlickrResponseModel.Photo> images) {
            int count = photos.size();
            photos.addAll(images);
            notifyItemRangeInserted(count, images.size());
        }

        public void deleteAllItems() {
            int count = photos.size();
            photos.clear();
            notifyItemRangeRemoved(0, count);
        }

        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ImageViewHolder(getActivity().
                    getLayoutInflater().
                    inflate(R.layout.card_image_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {
            FlickrResponseModel.Photo imageInfo = photos.get(position);

            Picasso.with(getActivity())
                    .load(Contants.getFlickrPhotoURL(
                            imageInfo.getFarm(),
                            imageInfo.getServer(),
                            imageInfo.getId(),
                            imageInfo.getSecret()))
                    .resize(350, 350)
                    .centerCrop()
                    .into(holder.holderImageView);
        }

        @Override
        public int getItemCount() {
            return photos.size();
        }
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.photo_image_view)
        protected ImageView holderImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}