package com.proj.andoid.nownews.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.proj.andoid.nownews.R;
import com.proj.andoid.nownews.ui.fragments.ImagesTabFragment;
import com.proj.andoid.nownews.ui.fragments.NewsTabFragment;
import com.proj.andoid.nownews.ui.fragments.PostTabFragment;
import com.proj.andoid.nownews.ui.tabs.SlidingTabLayout;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.tabs)
    protected SlidingTabLayout tabs;
    @Bind(R.id.main_view_pager)
    protected ViewPager mainPager;

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
                getResources().getStringArray(R.array.tab_titles)));
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(mainPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                    return new NewsTabFragment();
                case 1:
                    return new ImagesTabFragment();
                case 2:
                    return new PostTabFragment();
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
