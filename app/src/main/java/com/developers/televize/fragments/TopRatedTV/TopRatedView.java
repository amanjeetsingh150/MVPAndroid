package com.developers.televize.fragments.TopRatedTV;

import android.widget.ImageView;
import android.widget.TextView;

import com.developers.televize.model.TopRatedTvModel.TopRatedResult;

import java.util.List;

/**
 * Created by Amanjeet Singh on 18/7/17.
 */

public interface TopRatedView {

    void showTopRatedShows(List<TopRatedResult> topRatedResults);

    void showError(String error);

    void launchDetailActivity(TopRatedResult topRatedResult, ImageView topRatedBanner, TextView topRatedTitle);

}
