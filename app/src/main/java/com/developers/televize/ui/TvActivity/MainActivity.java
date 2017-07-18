package com.developers.televize.ui.TvActivity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.developers.televize.InitApplication;
import com.developers.televize.R;
import com.developers.televize.adapter.TvPagerAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    MainPresenter presenter;
    private TvPagerAdapter tvPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((InitApplication) getApplication()).getAppComponent().inject(this);
        toolbar.setTitle(R.string.toolbar_title);
        presenter.setView(this);
        presenter.loadTabs();
        tvPagerAdapter=new TvPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tvPagerAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Popular Shows"));
        tabLayout.addTab(tabLayout.newTab().setText("Top Rated Shows"));
    }

}
