package com.developers.televize.ui.fragments.PopularTv;

import android.content.Context;

import com.developers.televize.BuildConfig;
import com.developers.televize.data.DataManager;
import com.developers.televize.data.model.PopularTvModel.PageResult;
import com.developers.televize.data.model.PopularTvModel.Result;
import com.developers.televize.di.ApplicationContext;
import com.developers.televize.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public class PopularTvPresenter<V extends PopularTvView> extends BasePresenter<V> implements PopularTvMvpPresenter<V>{

    private DataManager dataManager;
    private List<Result> resultList;

    @Inject
    public PopularTvPresenter(@ApplicationContext Context context,DataManager dataManager) {
        super(context);
        this.dataManager=dataManager;
    }

    @Override
    public void getPopularShowsApi(int page) {
        if (page > 1) {
            getMVpView().showLoadMoreSpinner();
        }
        dataManager.getPopularTv(BuildConfig.TV_KEY, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PageResult>() {
                    Disposable disposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable=d;
                    }

                    @Override
                    public void onNext(PageResult value) {
                        resultList=value.getResults();
                        getMVpView().hideLoadMoreSpinner();
                        getMVpView().showData(resultList);
                        getMVpView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMVpView().hideLoadMoreSpinner();
                        getMVpView().showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        if(!disposable.isDisposed()){
                            disposable.dispose();
                        }
                    }
                });
    }
}
