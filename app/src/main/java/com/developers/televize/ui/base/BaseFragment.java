package com.developers.televize.ui.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.developers.televize.di.component.ActivityComponent;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public class BaseFragment extends Fragment implements BaseMvpView {

    private static final String TAG = BaseFragment.class.getSimpleName();
    private OnFragmentInteractionListener listener;

    @Override
    public void showError(String error) {

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            Log.d(TAG, "Parent Activity not implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface OnFragmentInteractionListener {
        void showErrorCallBack(String error);

        ActivityComponent getActivityComponentCallBack();
    }

    public ActivityComponent getActivityComponent(){
        return listener.getActivityComponentCallBack();
    }
}
