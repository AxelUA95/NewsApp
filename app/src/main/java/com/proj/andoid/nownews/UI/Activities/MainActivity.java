package com.proj.andoid.nownews.ui.activities;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.proj.andoid.nownews.R;
import com.proj.andoid.nownews.event.SearchByLocationEvent;
import com.proj.andoid.nownews.ui.fragments.ImagesFragment;
import com.proj.andoid.nownews.ui.fragments.NewsFragment;
import com.proj.andoid.nownews.ui.fragments.PostFragment;
import com.proj.andoid.nownews.ui.tabs.SlidingTabLayout;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    @Bind(R.id.main_toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.tabs)
    protected SlidingTabLayout tabs;
    @Bind(R.id.main_view_pager)
    protected ViewPager mainPager;

    private GoogleApiClient googleApiClient;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        mainPager.setAdapter(new ViewPagerAdapter(
                getSupportFragmentManager(),
                getResources().getStringArray(R.array.titles)));

        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(mainPager);
        //set up location api client
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(tag, "Connecting to location service");
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            Log.d(tag, "Disconnecting from location service");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_search_location: {
                searchByLocation();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void searchByLocation() {
        if (!googleApiClient.isConnected()) {
            googleApiClient.connect();
            return;
        }

        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        BUS.post(new SearchByLocationEvent(lastLocation));
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(tag, "Successfully connected to location service");
        searchByLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(tag, "GoogleApiClient suspended");
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(tag, "Error connecting to location service");
        Toast.makeText(this, "Cannot access location", Toast.LENGTH_SHORT).show();
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private String[] titles;

        public ViewPagerAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            this.titles = titles;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NewsFragment();
                case 1:
                    return new ImagesFragment();
                case 2:
                    return new PostFragment();
                default:
                    return null;
            }
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
