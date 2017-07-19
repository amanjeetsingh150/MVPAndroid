package com.developers.televize.rest;

import com.developers.televize.model.CharacterModel.CharacterResult;
import com.developers.televize.model.PopularTvModel.PageResult;
import com.developers.televize.model.TopRatedTvModel.TopRatedPageResult;
import com.developers.televize.model.TvShowDetailModel.TvShowDetailResult;
import com.developers.televize.model.VideoResultModel.VideoResults;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Amanjeet Singh on 17/7/17.
 */

public interface ApiInterface {

    @GET("popular")
    Call<PageResult> getTvShows(@Query("api_key") String key);

    @GET("top_rated")
    Observable<TopRatedPageResult> getTopRatedShows(@Query("api_key") String key);

    @GET("{tv_id}")
    Call<TvShowDetailResult> getShowDetail(@Path("tv_id") int tvId, @Query("api_key") String key);

    @GET("{tv_id}/credits")
    Call<CharacterResult> getCrew(@Path("tv_id") int tvId, @Query("api_key") String key);

    @GET("{tv_id}/videos")
    Call<VideoResults> getVideo(@Path("tv_id") int tvId, @Query("api_key") String key);


}
