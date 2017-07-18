package com.developers.televize.ui.TvActivity;

import android.content.Context;

import com.developers.televize.InitApplication;

/**
 * Created by Amanjeet Singh on 16/7/17.
 */

public class MainPresenterImpl implements MainPresenter{

    private MainView view;


    public MainPresenterImpl(Context context) {
        ((InitApplication)context).getAppComponent().inject(this);
    }

    @Override
    public void setView(MainView view) {
        this.view=view;
    }

    @Override
    public void loadTabs() {
        view.showTabs();
    }

}
