package com.developers.televize.ui.base;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
