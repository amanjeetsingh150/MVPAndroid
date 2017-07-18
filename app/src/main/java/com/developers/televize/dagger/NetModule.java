package com.developers.televize.dagger;

import com.developers.televize.Util.Constants;
import com.developers.televize.rest.ApiInterface;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Amanjeet Singh on 16/7/17.
 */
@Module
public class NetModule {

    private static final String BASE_URL="NAME_BASE_URL";

    @Provides
    @Named(BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(RxJava2CallAdapterFactory factory,
                             @Named(BASE_URL) String base_url,
                             Converter.Factory converter) {
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addCallAdapterFactory(factory)
                .addConverterFactory(converter)
                .build();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter(){
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }

}
