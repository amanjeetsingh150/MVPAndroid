package com.developers.televize.ui.DetailActivity;

import android.content.Context;
import android.util.Log;

import com.developers.televize.BuildConfig;
import com.developers.televize.InitApplication;
import com.developers.televize.model.CharacterModel.CharacterResult;
import com.developers.televize.model.PopularTvModel.Result;
import com.developers.televize.model.TvShowDetailModel.TvShowDetailResult;
import com.developers.televize.rest.ApiInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Amanjeet Singh on 19/7/17.
 */

public class DetailPresenterImpl implements DetailPresenter {


    private static final String TAG = DetailPresenterImpl.class.getSimpleName();
    @Inject
    ApiInterface apiInterface;
    private DetailView detailView;

    public DetailPresenterImpl(Context context) {
        ((InitApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setDetailView(DetailView detailView) {
        this.detailView = detailView;
    }

    @Override
    public void fetchDetails(Result result) {
        detailView.showPopularDetails(result);
    }

    @Override
    public void getDetailForShow(int id) {
        apiInterface.getShowDetail(id, BuildConfig.TV_KEY)
                .enqueue(new Callback<TvShowDetailResult>() {
                    @Override
                    public void onResponse(Call<TvShowDetailResult> call, Response<TvShowDetailResult> response) {
                        detailView.showDetailOfShows(response.body());
                    }

                    @Override
                    public void onFailure(Call<TvShowDetailResult> call, Throwable t) {
                        detailView.showError(t.getMessage());
                    }
                });
    }

    @Override
    public void getChar(int id) {
        apiInterface.getCrew(id, BuildConfig.TV_KEY)
                .enqueue(new Callback<CharacterResult>() {
                    @Override
                    public void onResponse(Call<CharacterResult> call, Response<CharacterResult> response) {
                        detailView.showCharacters(response.body().getCast());
                    }

                    @Override
                    public void onFailure(Call<CharacterResult> call, Throwable t) {
                        detailView.showError(t.getMessage());
                    }
                });
    }


}
