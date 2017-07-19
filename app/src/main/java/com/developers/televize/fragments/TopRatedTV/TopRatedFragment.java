package com.developers.televize.fragments.TopRatedTV;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.developers.televize.InitApplication;
import com.developers.televize.R;
import com.developers.televize.Util.Constants;
import com.developers.televize.adapter.TopRatedTvAdapter;
import com.developers.televize.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.ui.DetailActivity.DetailActivity;
import com.google.gson.Gson;

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
    private Gson gson;
    private Intent intent;

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
        topRatedTvAdapter = new TopRatedTvAdapter(getActivity(), topRatedResults);
        topRatedTvAdapter.setTopRatedTvView(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        topRatedRecyclerView.setLayoutManager(layoutManager);
        topRatedRecyclerView.setAdapter(topRatedTvAdapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchDetailActivity(TopRatedResult ratedResult, ImageView poster, TextView title) {
        gson = new Gson();
        intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.RATED_TV_KEY, gson.toJson(ratedResult));
        String key=Constants.RATED_KEY;
        intent.putExtra("KEY",key);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair<View, String> p1 = Pair.create((View) poster, getString(R.string.popular_tv_image_transition));
            Pair<View, String> p2 = Pair.create((View) title, getString(R.string.popular_tv_title_transition));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(getActivity(), p1, p2);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
