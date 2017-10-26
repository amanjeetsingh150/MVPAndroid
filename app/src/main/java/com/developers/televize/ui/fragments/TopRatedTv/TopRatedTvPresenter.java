package com.developers.televize.ui.fragments.TopRatedTv;

import android.content.Context;

import com.developers.televize.BuildConfig;
import com.developers.televize.data.DataManager;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedPageResult;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.di.ApplicationContext;
import com.developers.televize.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Amanjeet Singh on 21/8/17.
 */

public class TopRatedTvPresenter<V extends TopRatedTvView> extends BasePresenter<V> implements TopRatedTvMvpPresenter<V> {

    private DataManager dataManager;
    private List<TopRatedResult> topRatedResultList;

    @Inject
    public TopRatedTvPresenter(@ApplicationContext Context context, DataManager dataManager) {
        super(context);
        this.dataManager = dataManager;
    }

    @Override
    public void getTopRatedShows(int page) {
        if (page > 1) {
            getMVpView().showLoadMoreSpinner();
        }
        dataManager.getTopRatedTv(BuildConfig.TV_KEY, page)
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
                        topRatedResultList=value.getResults();
                        getMVpView().hideLoadMoreSpinner();
                        getMVpView().showTopRatedTv(topRatedResultList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMVpView().hideLoadMoreSpinner();
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
