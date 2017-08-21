package com.developers.televize.ui.fragments.PopularTv;

import android.widget.ImageView;
import android.widget.TextView;

import com.developers.coolprogressviews.DoubleArcProgress;
import com.developers.televize.data.model.PopularTvModel.Result;
import com.developers.televize.ui.base.BaseMvpView;

import java.util.List;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public interface PopularTvView extends BaseMvpView {

    void showData(List<Result> resultList);

    void launchDetailActivity(Result result, ImageView poster, TextView popularTitle);

    void launchShareActivity(String popularity);

    void hideLoading();
}
