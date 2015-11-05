package com.proj.andoid.localnews.ui.activities;

import android.content.res.TypedArray;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.proj.andoid.localnews.R;
import com.proj.andoid.localnews.events.LocationServiceEvent;
import com.proj.andoid.localnews.ui.fragments.ImagesFragment;
import com.proj.andoid.localnews.ui.fragments.PostsFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    @Bind(R.id.main_tabs)
    protected TabLayout tabs;
    @Bind(R.id.main_pager)
    protected ViewPager pager;
    @Bind(R.id.search_edit_text)
    protected EditText searchEditText;
    @Bind(R.id.search_popup_menu)
    protected ImageButton popupMenuButton;
    @Bind(R.id.search_image_view)
    protected ImageView searchImageView;

    private GoogleApiClient googleApiClient;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.titles)));
        pager.setOffscreenPageLimit(3);

        tabs.setupWithViewPager(pager);
        TypedArray typedArray = obtainStyledAttributes(
                new TypedValue().data, new int[] {R.attr.colorAccent});
        tabs.setSelectedTabIndicatorColor(typedArray.getColor(0, 0));
        typedArray.recycle();
        buildApiClient();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    private void buildApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void onLocationRequest() {
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        bus.post(new LocationServiceEvent(location));
    }

    @Override
    public void onConnected(Bundle bundle) {
        onLocationRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(tag, "Connection to location service is suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(tag, "Failed to connect location service" + connectionResult.getErrorMessage());
        if (!googleApiClient.isConnecting()) {
            googleApiClient.connect();
        }
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private String[] titles;

        public ViewPagerAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return new ImagesFragment();
                }
                case 1: {
                    return new Fragment();
                }
                case 2: {
                    return new PostsFragment();
                }
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
