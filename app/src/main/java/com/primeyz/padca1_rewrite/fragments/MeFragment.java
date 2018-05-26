package com.primeyz.padca1_rewrite.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.components.ProgressViewPod;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {

    @BindView(R.id.view_progress)
    ProgressViewPod progressView;


    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);

        progressView.setTvCurrentDayStreak(1);
        progressView.setTvTotalMinutes(5);
        progressView.setTvTotalSessions(1);

        return view;
    }

}
