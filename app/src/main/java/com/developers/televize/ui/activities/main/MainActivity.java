package com.developers.televize.ui.activities.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.developers.televize.R;
import com.developers.televize.di.component.ActivityComponent;
import com.developers.televize.ui.adapters.TvPagerAdapter;
import com.developers.televize.ui.base.BaseActivity;
import com.developers.televize.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView,BaseFragment.OnFragmentInteractionListener{

    @Inject
    MainMvpPresenter<MainView> mainMvpPresenter;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private TvPagerAdapter tvPagerAdapter;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.toolbar_title);
        setupTabs();
        mainMvpPresenter.attachView(this);
        mainMvpPresenter.loadPagerAdapter();
    }

    private void setupTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Popular Shows"));
        tabLayout.addTab(tabLayout.newTab().setText("Top Rated Shows"));
    }

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
    public void setPagerAdapter() {
        tvPagerAdapter=new TvPagerAdapter(getSupportFragmentManager());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setAdapter(tvPagerAdapter);
    }

    @Override
    public void showErrorCallBack(String error) {

    }

    @Override
    public ActivityComponent getActivityComponentCallBack() {
        return getActivityComponent();
    }
}
