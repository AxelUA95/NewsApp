package com.proj.andoid.localnews.config;

import com.proj.andoid.localnews.api.FlickrLoader;
import com.proj.andoid.localnews.ui.activities.BaseActivity;
import com.proj.andoid.localnews.ui.fragments.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * created by Alex Ivanov on 10.10.15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    void inject(FlickrLoader loader);
}
