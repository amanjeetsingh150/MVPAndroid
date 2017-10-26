package com.developers.televize.data;

import com.developers.televize.data.model.CharacterModel.CharacterResult;
import com.developers.televize.data.model.PopularTvModel.PageResult;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedPageResult;
import com.developers.televize.data.model.TvShowDetailModel.TvShowDetailResult;
import com.developers.televize.data.model.VideoResultModel.VideoResults;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Amanjeet Singh on 21/8/17.
 */

public interface ApiInterface {
    @GET("popular")
    Observable<PageResult> getTvShows(@Query("api_key") String key, @Query("page") int page);

    @GET("top_rated")
    Observable<TopRatedPageResult> getTopRatedShows(@Query("api_key") String key, @Query("page") int page);

    @GET("{tv_id}")
    Observable<TvShowDetailResult> getShowDetail(@Path("tv_id") int tvId, @Query("api_key") String key);

    @GET("{tv_id}/credits")
    Observable<CharacterResult> getCrew(@Path("tv_id") int tvId, @Query("api_key") String key);

    @GET("{tv_id}/videos")
    Call<VideoResults> getVideo(@Path("tv_id") int tvId, @Query("api_key") String key);
}
