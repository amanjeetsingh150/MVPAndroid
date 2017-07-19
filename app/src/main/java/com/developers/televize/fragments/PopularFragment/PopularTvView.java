package com.developers.televize.fragments.PopularFragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.developers.televize.model.PopularTvModel.Result;

import java.util.List;

/**
 * Created by Amanjeet Singh on 17/7/17.
 */

public interface PopularTvView {
    void showLoading();

    void hideLoading();

    void showData(List<Result> body);

    void showError(String error);

    void launchDetailActivity(Result result, ImageView poster, TextView popularTitle);
}
