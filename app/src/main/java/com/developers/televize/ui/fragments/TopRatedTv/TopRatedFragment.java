package com.developers.televize.ui.fragments.TopRatedTv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.developers.televize.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends Fragment {


    public TopRatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_top_rated, container, false);


        return view;
    }

}
