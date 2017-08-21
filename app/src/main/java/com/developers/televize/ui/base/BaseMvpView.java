package com.developers.televize.ui.base;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public interface BaseMvpView {

    void showError(String error);

    void showLoading();

    void hideLoading();

    boolean isNetworkAvailable();
}
