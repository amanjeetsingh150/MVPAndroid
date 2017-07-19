package com.developers.televize.ui.DetailActivity;

import com.developers.televize.model.CharacterModel.Cast;
import com.developers.televize.model.PopularTvModel.Result;
import com.developers.televize.model.TvShowDetailModel.TvShowDetailResult;

import java.util.List;

/**
 * Created by Amanjeet Singh on 19/7/17.
 */

public interface DetailView {
    void showPopularDetails(Result result);

    void showError(String error);

    void showDetailOfShows(TvShowDetailResult tvShowDetailResult);

    void showCharacters(List<Cast> castList);

}