package com.developers.televize.ui.activities.detail;

import com.developers.televize.data.model.PopularTvModel.Result;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.ui.base.Presenter;

/**
 * Created by Amanjeet Singh on 22/8/17.
 */

public interface DetailMvpPresenter<V extends DetailView> extends Presenter<V> {

    void fetchDetails(Result result);

    void fetchTopRatedResult(TopRatedResult ratedResult);

    void getDetailForShow(int id);

    void getChar(int id);

    void fetchVideos(int id);
}
