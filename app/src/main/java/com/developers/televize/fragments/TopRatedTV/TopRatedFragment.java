package com.developers.televize.fragments.TopRatedTV;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developers.televize.InitApplication;
import com.developers.televize.R;
import com.developers.televize.adapter.TopRatedTvAdapter;
import com.developers.televize.model.TopRatedTvModel.TopRatedResult;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends Fragment implements TopRatedView {

    private static final String TAG = TopRatedFragment.class.getSimpleName();
    @Inject
    TopRatedPresenter topRatedPresenter;

    @BindView(R.id.top_rated_list)
    RecyclerView topRatedRecyclerView;
    private TopRatedTvAdapter topRatedTvAdapter;

    public TopRatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top_rated, container, false);
        ButterKnife.bind(this, v);
        ((InitApplication) getActivity().getApplication()).getAppComponent().inject(this);
        topRatedPresenter.setTopRatedView(this);
        topRatedPresenter.getTopRatedShows();
        return v;
    }

    @Override
    public void showTopRatedShows(List<TopRatedResult> topRatedResults) {
        topRatedTvAdapter=new TopRatedTvAdapter(getActivity(),topRatedResults);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        topRatedRecyclerView.setLayoutManager(layoutManager);
        topRatedRecyclerView.setAdapter(topRatedTvAdapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
    }
}
