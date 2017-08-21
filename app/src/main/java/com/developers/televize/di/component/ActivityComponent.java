package com.developers.televize.di.component;

import com.developers.televize.di.PerActivity;
import com.developers.televize.di.module.ActivityModule;
import com.developers.televize.ui.activities.main.MainActivity;
import com.developers.televize.ui.fragments.PopularTv.PopularTvFragment;
import com.developers.televize.ui.fragments.TopRatedTv.TopRatedFragment;

import dagger.Component;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */
@PerActivity
@Component(modules = {ActivityModule.class}, dependencies = {ApplicationComponent.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(PopularTvFragment popularTvFragment);

    void inject(TopRatedFragment topRatedFragment);
}
