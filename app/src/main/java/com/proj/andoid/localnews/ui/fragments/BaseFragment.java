package com.proj.andoid.localnews.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proj.andoid.localnews.NewsApp;
import com.proj.andoid.localnews.config.AppDatabase;
import com.proj.andoid.localnews.config.AppPreferences;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * created by Alex Ivanov on 10.10.15.
 */
public abstract class BaseFragment extends Fragment {

    @Inject
    protected AppPreferences prefs;
    @Inject
    protected AppDatabase database;
    protected EventBus bus;

    protected final String tag = getClass().getName();

    protected abstract int getContentResource();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsApp.getComponent().inject(this);
        setRetainInstance(true);
        bus = EventBus.getDefault();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentResource(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
