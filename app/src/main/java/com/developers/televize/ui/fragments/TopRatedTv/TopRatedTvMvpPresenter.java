package com.developers.televize.ui.fragments.TopRatedTv;

import com.developers.televize.ui.base.Presenter;

/**
 * Created by Amanjeet Singh on 21/8/17.
 */

public interface TopRatedTvMvpPresenter<V extends TopRatedTvView> extends Presenter<V> {

    void getTopRatedShows();

}
