package com.developers.televize.ui.base;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public class BasePresenter<V extends BaseMvpView> implements Presenter<V>{

    private V view;
    private Context context;

    @Inject
    public BasePresenter(Context context){
        this.context=context;
    }

    @Override
    public void attachView(V view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        view=null;
    }

    public boolean isViewAttached(){
        return view!=null;
    }

    public V getMVpView(){
        return view;
    }
}
