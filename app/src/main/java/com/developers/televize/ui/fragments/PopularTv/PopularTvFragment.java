package com.developers.televize.ui.fragments.PopularTv;


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

import com.developers.coolprogressviews.DoubleArcProgress;
import com.developers.televize.R;
import com.developers.televize.data.model.PopularTvModel.Result;
import com.developers.televize.ui.activities.detail.DetailActivity;
import com.developers.televize.ui.adapters.PopularTvShowsAdapter;
import com.developers.televize.ui.base.BaseFragment;
import com.developers.televize.util.Constants;
import com.developers.televize.util.PaginationScrollListener;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularTvFragment extends BaseFragment implements PopularTvView {

    private static final int START_PAGE = 1;

    @Inject
    PopularTvMvpPresenter<PopularTvView> popularTvPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.double_arc_progress)
    DoubleArcProgress doubleArcProgress;
    private PopularTvShowsAdapter popularTvShowsAdapter;
    private Intent intent;
    private Gson gson;

    private PaginationScrollListener scrollListener;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        popularTvPresenter.attachView(this);
        popularTvPresenter.getPopularShowsApi(START_PAGE);
        popularTvShowsAdapter = new PopularTvShowsAdapter(getActivity());
        recyclerView.setAdapter(popularTvShowsAdapter);
        scrollListener = new PaginationScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                fetchPageData(currentPage);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        return v;
    }

    private void fetchPageData(int currentPage) {
        popularTvPresenter.getPopularShowsApi(currentPage);
    }

    @Override
    public void showData(List<Result> resultList) {
        popularTvShowsAdapter.addData(resultList);
        popularTvShowsAdapter.setPopularTvView(this);
    }

    @Override
    public void launchDetailActivity(Result result, ImageView poster, TextView popularTitle) {
        gson = new Gson();
        intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.POPULAR_TV_KEY, gson.toJson(result));
        String key= Constants.POPULAR_KEY;
        intent.putExtra("KEY",key);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Pair<View, String> p1 = Pair.create((View)poster, getString(R.string.popular_tv_image_transition));
            Pair<View, String> p2 = Pair.create((View)popularTitle, getString(R.string.popular_tv_title_transition));
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(getActivity(),p1,p2);
            startActivity(intent,options.toBundle());
        }
        else{
            startActivity(intent);
        }
    }

    @Override
    public void launchShareActivity(String popularity) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.setAction(Intent.ACTION_SEND);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, popularity);
        startActivity(sharingIntent);
    }

    @Override
    public void hideLoading() {
        doubleArcProgress.setVisibility(View.GONE);
    }

    @Override
    public void showLoadMoreSpinner() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                popularTvShowsAdapter.addLoadingFooter();
            }
        });
    }

    @Override
    public void hideLoadMoreSpinner() {
        popularTvShowsAdapter.removeLoadingFooter();
    }
}
