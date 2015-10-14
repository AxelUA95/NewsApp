package com.proj.andoid.localnews.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.proj.andoid.localnews.NewsApp;
import com.proj.andoid.localnews.config.AppPreferences;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * created by Alex Ivanov on 10.10.15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected final String tag = getClass().getName();
    @Inject
    protected AppPreferences prefs;
    protected EventBus bus;

    protected abstract int getContentResource();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResource());
        NewsApp.getComponent().inject(this);
        ButterKnife.bind(this);
        bus = EventBus.getDefault();
    }
}
