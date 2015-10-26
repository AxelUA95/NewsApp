package com.proj.andoid.localnews.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.proj.andoid.localnews.R;
import com.proj.andoid.localnews.events.LoadByTagEvent;
import com.proj.andoid.localnews.events.LocationServiceEvent;
import com.proj.andoid.localnews.ui.fragments.ImagesFragment;
import com.proj.andoid.localnews.ui.fragments.PostsFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements
        SearchView.OnQueryTextListener,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    @Bind(R.id.main_toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.main_tabs)
    protected TabLayout tabs;
    @Bind(R.id.main_pager)
    protected ViewPager pager;

    private GoogleApiClient googleApiClient;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.titles)));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            searchView.setElevation(5);
        }
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
        return true;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search_location) {
            onLocationRequest();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        bus.post(new LoadByTagEvent(query));
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
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
