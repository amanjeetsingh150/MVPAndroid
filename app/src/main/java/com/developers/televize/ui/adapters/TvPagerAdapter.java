package com.developers.televize.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.developers.televize.ui.fragments.PopularTv.PopularTvFragment;
import com.developers.televize.ui.fragments.TopRatedTv.TopRatedFragment;

/**
 * Created by Amanjeet Singh on 20/8/17.
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
