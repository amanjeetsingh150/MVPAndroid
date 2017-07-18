package com.developers.televize.fragments.PopularFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.developers.coolprogressviews.DoubleArcProgress;
import com.developers.televize.InitApplication;
import com.developers.televize.R;
import com.developers.televize.adapter.PopularTvShowsAdapter;
import com.developers.televize.model.PopularTvModel.Result;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularTvFragment extends Fragment implements PopularTvView {


    private static final String TAG = PopularTvFragment.class.getSimpleName();
    @Inject
    PopularTvPresenter popularTvPresenter;
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
        View view = inflater.inflate(R.layout.fragment_poular_tv, container, false);
        ButterKnife.bind(this, view);
        ((InitApplication) getActivity().getApplication()).getAppComponent().inject(this);
        popularTvPresenter.setView(this);
        popularTvPresenter.getShows();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showLoading() {
        doubleArcProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        doubleArcProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showData(List<Result> body) {
        Log.d(TAG, "" + body.size());
        popularTvShowsAdapter = new PopularTvShowsAdapter(getActivity(), body);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(popularTvShowsAdapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

}
