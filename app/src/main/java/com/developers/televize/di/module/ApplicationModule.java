package com.developers.televize.di.module;

import android.app.Application;
import android.content.Context;

import com.developers.televize.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */
@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application providesApplication(){
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return application;
    }


}
