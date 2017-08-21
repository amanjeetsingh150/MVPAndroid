package com.developers.televize.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.developers.televize.InitApplication;
import com.developers.televize.di.component.ActivityComponent;
import com.developers.televize.di.component.DaggerActivityComponent;
import com.developers.televize.di.module.ActivityModule;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public class BaseActivity extends AppCompatActivity implements BaseMvpView{

    private ActivityComponent activityComponent;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(InitApplication.get(this).component())
                    .build();
        }
        return activityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public boolean isNetworkAvailable() {
        return false;
    }
}
