package com.developers.televize.ui.DetailActivity;

import com.developers.televize.model.PopularTvModel.Result;
import com.developers.televize.model.TopRatedTvModel.TopRatedResult;

/**
 * Created by Amanjeet Singh on 19/7/17.
 */

public interface DetailPresenter {

    void setDetailView(DetailView detailView);

    void fetchDetails(Result result);

    void fetchTopRatedResult(TopRatedResult ratedResult);

    void getDetailForShow(int id);

    void getChar(int id);

    void fetchVideos(int id);
}
