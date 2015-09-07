package com.proj.andoid.nownews.config;

import com.proj.andoid.nownews.ui.activities.BaseActivity;
import com.proj.andoid.nownews.ui.fragments.BaseFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * created by Alex Ivanov on 05.09.15.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

}
