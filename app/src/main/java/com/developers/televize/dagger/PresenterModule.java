package com.developers.televize.dagger;

import android.content.Context;

import com.developers.televize.fragments.PopularFragment.PopularTvPresenter;
import com.developers.televize.fragments.PopularFragment.PopularTvPresenterImpl;
import com.developers.televize.fragments.TopRatedTV.TopRatedPresenter;
import com.developers.televize.fragments.TopRatedTV.TopRatedPresenterImpl;
import com.developers.televize.ui.TvActivity.MainPresenter;
import com.developers.televize.ui.TvActivity.MainPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Amanjeet Singh on 16/7/17.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    protected MainPresenter provideMainPresenter(Context context){
        return new MainPresenterImpl(context);
    }

    @Provides
    @Singleton
    protected PopularTvPresenter providesPopularTvPresenter(Context context){
        return new PopularTvPresenterImpl(context);
    }

    @Provides
    @Singleton
    protected TopRatedPresenter providesTopRatedPresenter(Context context){
        return new TopRatedPresenterImpl(context);
    }
}
