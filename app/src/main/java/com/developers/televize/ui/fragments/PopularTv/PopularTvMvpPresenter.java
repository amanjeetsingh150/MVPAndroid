package com.developers.televize.ui.fragments.PopularTv;

import com.developers.televize.ui.base.Presenter;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public interface PopularTvMvpPresenter<V extends PopularTvView> extends Presenter<V> {
    void getPopularShowsApi();
}
