package com.proj.andoid.nownews.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proj.andoid.nownews.NewsApp;
import com.proj.andoid.nownews.config.AppPreferences;

import javax.inject.Inject;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * created by Alex Ivanov on 05.09.15.
 */
public abstract class BaseFragment extends Fragment{

    protected final String tag = getClass().getName();

    @Inject
    protected AppPreferences prefs;
    protected EventBus BUS;

    protected abstract int getContentView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewsApp.getComponent().inject(this);
        setRetainInstance(true);
        BUS = EventBus.getDefault();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
