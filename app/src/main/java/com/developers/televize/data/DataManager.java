package com.developers.televize.data;

import com.developers.televize.data.model.PopularTvModel.PageResult;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */
@Singleton
public class DataManager {

    ApiHelper apiHelper;

    @Inject
    public DataManager(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    public Observable<PageResult> getPopularTv(String key){
        return apiHelper.getTvShows(key);
    }
}
