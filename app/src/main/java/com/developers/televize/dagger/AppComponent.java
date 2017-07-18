package com.developers.televize.dagger;


import com.developers.televize.fragments.PopularFragment.PopularTvFragment;
import com.developers.televize.fragments.PopularFragment.PopularTvPresenterImpl;
import com.developers.televize.fragments.TopRatedTV.TopRatedFragment;
import com.developers.televize.fragments.TopRatedTV.TopRatedPresenterImpl;
import com.developers.televize.ui.TvActivity.MainActivity;
import com.developers.televize.ui.TvActivity.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Amanjeet Singh on 16/7/17.
 */

@Singleton
@Component(modules = {AppModule.class,
        PresenterModule.class,
        NetModule.class})
public interface AppComponent {

    void inject(MainActivity target);

    void inject(MainPresenterImpl target);

    void inject(PopularTvFragment target);

    void inject(PopularTvPresenterImpl target);

    void inject(TopRatedFragment target);

    void inject(TopRatedPresenterImpl target);
}
