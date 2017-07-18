package com.developers.televize.fragments.PopularFragment;

import android.content.Context;
import android.util.Log;

import com.developers.televize.BuildConfig;
import com.developers.televize.InitApplication;
import com.developers.televize.model.PopularTvModel.PageResult;
import com.developers.televize.model.PopularTvModel.Result;
import com.developers.televize.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Amanjeet Singh on 17/7/17.
 */

public class PopularTvPresenterImpl implements PopularTvPresenter {

    private static final String TAG = PopularTvPresenterImpl.class.getSimpleName();
    @Inject
    ApiInterface apiInterface;
    private PopularTvView view;
    private List<Result> tvShows;

    public PopularTvPresenterImpl(Context context) {
        ((InitApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setView(PopularTvView view) {
        this.view = view;
    }

    @Override
    public void getShows() {
        Log.d(TAG, "in getSHows");
        view.showLoading();
        tvShows = new ArrayList<>();
        apiInterface.getTvShows(BuildConfig.TV_KEY)
                .enqueue(new Callback<PageResult>() {
                    @Override
                    public void onResponse(Call<PageResult> call, Response<PageResult> response) {
                        view.hideLoading();
                        if (response.body().getResults().size() > 0) {
                            tvShows = response.body().getResults();
                            view.showData(tvShows);
                        } else {
                            view.showError("No results Found");
                        }
                    }

                    @Override
                    public void onFailure(Call<PageResult> call, Throwable t) {
                        view.showError(t.getMessage());
                    }
                });

    }
}
