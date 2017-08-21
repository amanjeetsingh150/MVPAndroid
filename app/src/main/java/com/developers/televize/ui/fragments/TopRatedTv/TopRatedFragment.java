package com.developers.televize.ui.fragments.TopRatedTv;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.televize.R;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.ui.adapters.TopRatedTvAdapter;
import com.developers.televize.ui.base.BaseFragment;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends BaseFragment implements TopRatedTvView {


    @Inject
    TopRatedTvMvpPresenter<TopRatedTvView> topRatedTvMvpPresenter;
    @BindView(R.id.top_rated_list)
    RecyclerView topRatedRecyclerView;
    private TopRatedTvAdapter topRatedTvAdapter;
    private Gson gson;
    private Intent intent;

    public TopRatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_rated, container, false);
        getActivityComponent().inject(this);
        topRatedTvMvpPresenter.attachView(this);
        topRatedTvMvpPresenter.getTopRatedShows();
        return view;
    }

    @Override
    public void showTopRatedTv(List<TopRatedResult> topRatedResults){
        topRatedTvAdapter = new TopRatedTvAdapter(getActivity(), topRatedResults);
        topRatedTvAdapter.setTopRatedTvView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        topRatedRecyclerView.setLayoutManager(layoutManager);
        topRatedRecyclerView.setAdapter(topRatedTvAdapter);
    }

    @Override
    public void launchDetailActivity(TopRatedResult topRatedResult, ImageView poster, TextView popularTitle) {

    }

    @Override
    public void launchShareActivity(String popularity) {

    }
}
