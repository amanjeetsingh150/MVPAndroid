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
 * Created by Amanjeet Singh on 21/8/17.
 */
@Singleton
public class ApiHelper {

    public ApiInterface apiInterface;

    @Inject
    ApiHelper(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Observable<PageResult> getTvShows(String key, int page) {
        return apiInterface.getTvShows(key, page);
    }

    public Observable<TopRatedPageResult> getTopRatedShows(String key, int page) {
        return apiInterface.getTopRatedShows(key, page);
    }

    public Observable<TvShowDetailResult> getDetails(int id, String key) {
        return apiInterface.getShowDetail(id, key);
    }

    public Observable<CharacterResult> getCharacters(int id, String key) {
        return apiInterface.getCrew(id, key);
    }

    public Call<VideoResults> getVideos(int id, String key) {
        return apiInterface.getVideo(id, key);
    }


}
