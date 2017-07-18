package com.developers.televize.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.developers.televize.fragments.PopularFragment.PopularTvFragment;
import com.developers.televize.fragments.TopRatedTV.TopRatedFragment;

/**
 * Created by Amanjeet Singh on 16/7/17.
 */

public class TvPagerAdapter extends FragmentPagerAdapter {

    public TvPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PopularTvFragment();
            case 1:
                return new TopRatedFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
