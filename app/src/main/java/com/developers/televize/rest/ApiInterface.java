package com.developers.televize.rest;

import com.developers.televize.model.PopularTvModel.PageResult;
import com.developers.televize.model.TopRatedTvModel.TopRatedPageResult;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Amanjeet Singh on 17/7/17.
 */

public interface ApiInterface {

    @GET("popular")
    Call<PageResult> getTvShows(@Query("api_key") String key);

    @GET("top_rated")
    Observable<TopRatedPageResult> getTopRatedShows(@Query("api_key") String key);

    //40f46bdfb85369f65325a2f2675ad187
}
