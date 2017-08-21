package com.developers.televize.ui.activities.detail;

import com.developers.televize.data.model.CharacterModel.Cast;
import com.developers.televize.data.model.PopularTvModel.Result;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.data.model.TvShowDetailModel.TvShowDetailResult;
import com.developers.televize.data.model.VideoResultModel.VideoIdResult;
import com.developers.televize.ui.base.BaseMvpView;

import java.util.List;

/**
 * Created by Amanjeet Singh on 22/8/17.
 */

public interface DetailView extends BaseMvpView {
    void showPopularDetails(Result result);

    void showError(String error);

    void showDetailOfShows(TvShowDetailResult tvShowDetailResult);

    void showCharacters(List<Cast> castList);

    void showTopRatedDetails(TopRatedResult result);

    void launchYoutubeActivity(List<VideoIdResult> videoIdResults);
}
