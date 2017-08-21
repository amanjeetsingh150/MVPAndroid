package com.developers.televize.ui.activities.main;

import android.content.Context;

import com.developers.televize.di.ApplicationContext;
import com.developers.televize.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public class MainPresenter<V extends MainView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(@ApplicationContext Context context) {
        super(context);
    }

    @Override
    public void loadPagerAdapter() {
        getMVpView().setPagerAdapter();
    }
}
