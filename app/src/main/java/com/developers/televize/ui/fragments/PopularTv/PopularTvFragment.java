package com.developers.televize.ui.fragments.PopularTv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.coolprogressviews.DoubleArcProgress;
import com.developers.televize.R;
import com.developers.televize.data.model.PopularTvModel.Result;
import com.developers.televize.ui.adapters.PopularTvShowsAdapter;
import com.developers.televize.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularTvFragment extends BaseFragment implements PopularTvView {


    @Inject
    PopularTvMvpPresenter<PopularTvView> popularTvPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.double_arc_progress)
    DoubleArcProgress doubleArcProgress;
    private PopularTvShowsAdapter popularTvShowsAdapter;


    public PopularTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_popular_tv, container, false);
        getActivityComponent().inject(this);
        ButterKnife.bind(this, v);
        popularTvPresenter.attachView(this);
        popularTvPresenter.getPopularShowsApi();
        return v;
    }

    @Override
    public void showData(List<Result> resultList) {
        popularTvShowsAdapter = new PopularTvShowsAdapter(getActivity(), resultList);
        popularTvShowsAdapter.setPopularTvView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(popularTvShowsAdapter);
    }

    @Override
    public void launchDetailActivity(Result result, ImageView poster, TextView popularTitle) {

    }

    @Override
    public void launchShareActivity(String popularity) {

    }

    @Override
    public void hideLoading() {
        doubleArcProgress.setVisibility(View.GONE);
    }
}
