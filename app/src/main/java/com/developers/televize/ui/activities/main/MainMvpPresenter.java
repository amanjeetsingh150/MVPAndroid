package com.developers.televize.ui.activities.main;

import com.developers.televize.ui.base.Presenter;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public interface MainMvpPresenter<V extends MainView> extends Presenter<V> {

    void loadPagerAdapter();
}
