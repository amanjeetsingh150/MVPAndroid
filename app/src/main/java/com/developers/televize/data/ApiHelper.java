package com.developers.televize.data;

import com.developers.televize.data.model.CharacterModel.CharacterResult;
import com.developers.televize.data.model.PopularTvModel.PageResult;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedPageResult;
import com.developers.televize.data.model.TvShowDetailModel.TvShowDetailResult;
import com.developers.televize.data.model.VideoResultModel.VideoResults;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Amanjeet Singh on 21/8/17.
 */
@Singleton
public class ApiHelper {

    public ApiInterface apiInterface;

    @Inject
    ApiHelper(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Observable<PageResult> getTvShows(String key) {
        return apiInterface.getTvShows(key);
    }

    public Observable<TopRatedPageResult> getTopRatedShows(String key) {
        return apiInterface.getTopRatedShows(key);
    }

    public Observable<TvShowDetailResult> getDetails(int id, String key) {
        return apiInterface.getShowDetail(id, key);
    }

    public Observable<CharacterResult> getCharacters(int id, String key) {
        return apiInterface.getCrew(id, key);
    }

    public Observable<VideoResults> getVideos(int id, String key) {
        return apiInterface.getVideo(id, key);
    }


}
