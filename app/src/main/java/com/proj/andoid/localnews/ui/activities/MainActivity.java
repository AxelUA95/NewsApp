package com.proj.andoid.localnews.ui.activities;

import android.app.SearchManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.proj.andoid.localnews.R;
import com.proj.andoid.localnews.ui.fragments.ImagesFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    @Bind(R.id.main_toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.main_tabs)
    protected TabLayout tabs;
    @Bind(R.id.main_pager)
    protected ViewPager pager;

    private Location lastLocation;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),
                getResources().getStringArray(R.array.titles)));

        tabs.setupWithViewPager(pager);
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.tabsScrollColor));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            searchView.setElevation(5);
            searchView.setBackgroundColor(Color.WHITE);
        }
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search_location) {
            Snackbar.make(pager, "comming soon", Snackbar.LENGTH_SHORT).show();
            //TODO search by location
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
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
                    return new Fragment();
                }
                case 1: {
                    return new ImagesFragment();
                }
                case 2: {
                    return new Fragment();
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
