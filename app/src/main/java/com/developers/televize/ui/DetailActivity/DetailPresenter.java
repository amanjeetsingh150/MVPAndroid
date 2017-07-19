package com.developers.televize.ui.DetailActivity;

import com.developers.televize.model.PopularTvModel.Result;

/**
 * Created by Amanjeet Singh on 19/7/17.
 */

public interface DetailPresenter {

    void setDetailView(DetailView detailView);

    void fetchDetails(Result result);

    void getDetailForShow(int id);

    void getChar(int id);
}
