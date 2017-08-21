package com.developers.televize;

import android.app.Application;
import android.content.Context;

import com.developers.televize.di.component.ApplicationComponent;
import com.developers.televize.di.component.DaggerApplicationComponent;
import com.developers.televize.di.module.ApplicationModule;

/**
 * Created by Amanjeet Singh on 16/7/17.
 */

public class InitApplication extends Application {

    ApplicationComponent applicationComponent;

    public static InitApplication get(Context context) {
        return (InitApplication) context.getApplicationContext();
    }

    public ApplicationComponent component() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }

}
