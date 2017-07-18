package com.developers.televize;

import android.app.Application;

import com.developers.televize.dagger.AppComponent;
import com.developers.televize.dagger.AppModule;
import com.developers.televize.dagger.DaggerAppComponent;
import com.developers.televize.dagger.NetModule;
import com.developers.televize.dagger.PresenterModule;

/**
 * Created by Amanjeet Singh on 16/7/17.
 */

public class InitApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent=initDagger(this);
    }

    protected AppComponent initDagger(InitApplication application){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .netModule(new NetModule())
                .presenterModule(new PresenterModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
