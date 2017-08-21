package com.developers.televize.ui.fragments.TopRatedTv;

import android.content.Context;

import com.developers.televize.data.DataManager;
import com.developers.televize.di.ApplicationContext;
import com.developers.televize.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Amanjeet Singh on 21/8/17.
 */

public class TopRatedTvPresenter<V extends TopRatedTvView> extends BasePresenter<V> implements TopRatedTvMvpPresenter<V> {

    private DataManager dataManager;
    @Inject
    public TopRatedTvPresenter(@ApplicationContext Context context,DataManager dataManager) {
        super(context);
        this.dataManager=dataManager;
    }

    @Override
    public void getTopRatedShows() {

    }
}
