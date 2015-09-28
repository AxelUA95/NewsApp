package com.proj.andoid.nownews.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.proj.andoid.nownews.R;
import com.proj.andoid.nownews.ui.fragments.ImagesFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.drawer_recycler_view)
    protected RecyclerView drawerRecyclerView;

    private FragmentManager fragmentManager;


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        setupDrawer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentManager = getSupportFragmentManager();
        useFragment(new ImagesFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
