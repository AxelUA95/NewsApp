package com.proj.andoid.nownews.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.proj.andoid.nownews.NewsApp;
import com.proj.andoid.nownews.config.AppPreferences;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * created by Alex Ivanov on 05.09.15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected final String tag = getClass().getName();
    @Inject
    protected AppPreferences prefs;
    protected EventBus BUS;

    protected abstract int getContentView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        NewsApp.getComponent().inject(this);
        ButterKnife.bind(this);
        BUS = EventBus.getDefault();
    }
}
