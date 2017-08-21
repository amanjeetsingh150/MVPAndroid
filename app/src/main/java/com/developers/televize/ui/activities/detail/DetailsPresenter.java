package com.developers.televize.ui.activities.detail;

import android.content.Context;

import com.developers.televize.BuildConfig;
import com.developers.televize.data.DataManager;
import com.developers.televize.data.model.CharacterModel.CharacterResult;
import com.developers.televize.data.model.PopularTvModel.Result;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.data.model.TvShowDetailModel.TvShowDetailResult;
import com.developers.televize.data.model.VideoResultModel.VideoResults;
import com.developers.televize.di.ApplicationContext;
import com.developers.televize.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Amanjeet Singh on 22/8/17.
 */

public class DetailsPresenter<V extends DetailView> extends BasePresenter<V> implements DetailMvpPresenter<V> {

    private DataManager dataManager;

    @Inject
    public DetailsPresenter(@ApplicationContext Context context, DataManager dataManager) {
        super(context);
        this.dataManager = dataManager;
    }


    @Override
    public void fetchDetails(Result result) {
        getMVpView().showPopularDetails(result);
    }

    @Override
    public void fetchTopRatedResult(TopRatedResult ratedResult) {
        getMVpView().showTopRatedDetails(ratedResult);
    }

    @Override
    public void getDetailForShow(int id) {
        dataManager.getTvDetail(id, BuildConfig.TV_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TvShowDetailResult>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(TvShowDetailResult value) {
                        getMVpView().showDetailOfShows(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMVpView().showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (!disposable.isDisposed())
                            disposable.dispose();
                    }
                });

    }

    @Override
    public void getChar(int id) {
        dataManager.getCharacters(id, BuildConfig.TV_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharacterResult>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(CharacterResult value) {
                        getMVpView().showCharacters(value.getCast());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMVpView().showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (!disposable.isDisposed())
                            disposable.dispose();
                    }
                });
    }

    @Override
    public void fetchVideos(int id) {
        dataManager.getVideos(id, BuildConfig.TV_KEY)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoResults>() {
                    Disposable disposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(VideoResults value) {
                        getMVpView().launchYoutubeActivity(value.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMVpView().showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if (!disposable.isDisposed())
                            disposable.dispose();
                    }
                });
    }
}
