package com.developers.televize.ui.fragments.TopRatedTv;

import android.widget.ImageView;
import android.widget.TextView;

import com.developers.televize.data.model.PopularTvModel.Result;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.ui.base.BaseMvpView;

import java.util.List;

/**
 * Created by Amanjeet Singh on 21/8/17.
 */

public interface TopRatedTvView extends BaseMvpView{

    void showTopRatedTv(List<TopRatedResult> topRatedResults);

    void launchDetailActivity(TopRatedResult topRatedResult, ImageView poster, TextView popularTitle);

    void launchShareActivity(String popularity);

    void hideLoading();

}
