package com.proj.andoid.nownews.ui.activities;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.proj.andoid.nownews.R;
import com.proj.andoid.nownews.event.SearchByLocationEvent;
import com.proj.andoid.nownews.ui.fragments.ImagesFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    @Bind(R.id.main_toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.drawer_recycler_view)
    protected RecyclerView drawerRecyclerView;

    private FragmentManager fragmentManager;
    private GoogleApiClient googleApiClient;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        setupDrawer();

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
        fragmentManager = getSupportFragmentManager();
        useFragment(new ImagesFragment());
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

    private void setupDrawer() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        drawerRecyclerView.setHasFixedSize(true);
        drawerRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        drawerRecyclerView.setAdapter(new DrawerAdapter(this));
    }

    private void useFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();
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

    private enum DrawerItem {

        NEWS(R.drawable.news),
        IMAGE(R.drawable.image),
        POST(R.drawable.post),
        SETTING(R.drawable.settings);

        private int itemIcon;

        DrawerItem(int icon) {
            itemIcon = icon;
        }

    }

    private class DrawerAdapter extends RecyclerView.Adapter<DrawerViewHolder>{

        private LayoutInflater inflater;

        DrawerAdapter(Context c) {
            inflater = LayoutInflater.from(c);
        }

        @Override
        public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DrawerViewHolder(inflater.inflate(R.layout.drawer_item, parent, false));
        }

        @Override
        public void onBindViewHolder(DrawerViewHolder holder, int position) {
            holder.itemImageView.setImageResource(DrawerItem.values()[position].itemIcon);
        }

        @Override
        public int getItemCount() {
            return DrawerItem.values().length;
        }
    }

    private class DrawerViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemImageView;

        public DrawerViewHolder(View itemView) {
            super(itemView);
            itemImageView = (ImageView) itemView.findViewById(R.id.drawer_item_image_view);
        }
    }
}
