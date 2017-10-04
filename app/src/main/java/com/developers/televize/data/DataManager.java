package com.developers.televize.data;

import com.developers.televize.data.model.CharacterModel.CharacterResult;
import com.developers.televize.data.model.PopularTvModel.PageResult;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedPageResult;
import com.developers.televize.data.model.TvShowDetailModel.TvShowDetailResult;
import com.developers.televize.data.model.VideoResultModel.VideoResults;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Call;

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

    public Observable<PageResult> getPopularTv(String key, int page) {
        return apiHelper.getTvShows(key, page);
    }

    public Observable<TopRatedPageResult> getTopRatedTv(String key, int page) {
        return apiHelper.getTopRatedShows(key, page);
    }

    public Observable<TvShowDetailResult> getTvDetail(int id, String key) {
        return apiHelper.getDetails(id, key);
    }

    public Observable<CharacterResult> getCharacters(int id, String key) {
        return apiHelper.getCharacters(id, key);
    }

    public Call<VideoResults> getVideos(int id, String key) {
        return apiHelper.getVideos(id, key);
    }

}
