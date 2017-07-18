package com.developers.televize.fragments.TopRatedTV;

import android.content.Context;
import android.util.Log;

import com.developers.televize.BuildConfig;
import com.developers.televize.InitApplication;
import com.developers.televize.model.PopularTvModel.PageResult;
import com.developers.televize.model.TopRatedTvModel.TopRatedPageResult;
import com.developers.televize.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.rest.ApiInterface;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Amanjeet Singh on 18/7/17.
 */

public class TopRatedPresenterImpl implements TopRatedPresenter {

    private static final String TAG = TopRatedPresenterImpl.class.getSimpleName();
    @Inject
    ApiInterface apiInterface;
    private TopRatedView view;
    List<TopRatedResult> topRatedResults;

    public TopRatedPresenterImpl(Context context) {
        ((InitApplication) context).getAppComponent().inject(this);
    }

    @Override
    public void setTopRatedView(TopRatedView view) {
        this.view = view;
    }

    @Override
    public void getTopRatedShows() {
        Log.d(TAG, "in Get top Rated");
        apiInterface.getTopRatedShows(BuildConfig.TV_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TopRatedPageResult>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(TopRatedPageResult value) {
                        topRatedResults=value.getResults();
                        view.showTopRatedShows(topRatedResults);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
