package com.developers.televize.di.component;

import android.content.Context;

import com.developers.televize.data.DataManager;
import com.developers.televize.di.ApplicationContext;
import com.developers.televize.di.PerActivity;
import com.developers.televize.di.module.ApplicationModule;
import com.developers.televize.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetModule.class})
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    DataManager dataManager();

}
