package com.primeyz.padca1_rewrite.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.adapters.SeriesRVAdapter;
import com.primeyz.padca1_rewrite.data.vo.BaseVO;
import com.primeyz.padca1_rewrite.events.RestApiEvent;
import com.primeyz.padca1_rewrite.viewholders.CurrentProgramViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by yepyaesonetun on 5/19/18.
 **/

public class SeriesFragment extends Fragment {

    SeriesRVAdapter adapter;
    RecyclerView rvSeries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_series, container, false);

        rvSeries = view.findViewById(R.id.rv_main);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvSeries.setLayoutManager(lm);
        adapter = new SeriesRVAdapter(getContext());
        rvSeries.setAdapter(adapter);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCurrentProgramsDataLoaded(RestApiEvent.currentProgramDataLoadedEvent event) {
        if (event.getLoadedCurrentProgram() != null) {
            adapter.addNewData(event.getLoadedCurrentProgram());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCategoriesDataLoaded(RestApiEvent.CategoriesDataLoadedEvent event) {
        if (event.getLoadCategories() != null
                && event.getLoadCategories().size() > 0) {
//            adapter.appendNewData(event.getLoadCategories().get(0).getPrograms());

            if (event.getLoadCategories().size() > 1) {
//                adapter.appendNewData(event.getLoadCategories().get(1).getPrograms());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTopicsDataLoaded(RestApiEvent.TopicsDataLoadedEvent event) {
        //mNewsAdapter.appendNewData(event.getLoadNews());
        if (event.getLoadTopics() != null && event.getLoadTopics().size() > 0) {
//            adapter.setNewData(event.getLoadTopics());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvSeries, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
