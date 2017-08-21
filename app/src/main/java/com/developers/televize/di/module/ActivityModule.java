package com.developers.televize.di.module;

import android.app.Activity;
import android.content.Context;

import com.developers.televize.di.ActivityContext;
import com.developers.televize.di.PerActivity;
import com.developers.televize.ui.activities.main.MainMvpPresenter;
import com.developers.televize.ui.activities.main.MainPresenter;
import com.developers.televize.ui.activities.main.MainView;
import com.developers.televize.ui.fragments.PopularTv.PopularTvMvpPresenter;
import com.developers.televize.ui.fragments.PopularTv.PopularTvPresenter;
import com.developers.televize.ui.fragments.PopularTv.PopularTvView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Activity providesActivity() {
        return activity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return activity;
    }

    @PerActivity
    @Provides
    MainMvpPresenter<MainView> providesMainPresenter(MainPresenter<MainView> mainMvpPresenter) {
        return mainMvpPresenter;
    }

    @PerActivity
    @Provides
    PopularTvMvpPresenter<PopularTvView> providesPopularTvPresenter(PopularTvPresenter<PopularTvView> popularTvPresenter) {
        return popularTvPresenter;
    }
}
