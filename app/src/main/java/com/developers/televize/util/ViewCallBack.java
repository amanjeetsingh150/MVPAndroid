package com.developers.televize.util;

import com.developers.televize.ui.fragments.PopularTv.PopularTvView;
import com.developers.televize.ui.fragments.TopRatedTv.TopRatedTvView;

/**
 * Created by Amanjeet Singh on 20/8/17.
 */

public interface ViewCallBack {
    void setPopularTvView(PopularTvView view);

    void setTopRatedTvView(TopRatedTvView view);
}
