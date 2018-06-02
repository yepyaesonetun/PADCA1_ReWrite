package com.primeyz.padca1_rewrite.fragments;

import android.content.Intent;
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
import com.primeyz.padca1_rewrite.activities.ProgramDetailActivity;
import com.primeyz.padca1_rewrite.adapters.SeriesRVAdapter;
import com.primeyz.padca1_rewrite.delegates.ProgramDelegate;
import com.primeyz.padca1_rewrite.events.RestApiEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/19/18.
 **/

public class SeriesFragment extends Fragment implements ProgramDelegate{

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    private SeriesRVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_series, container, false);
        ButterKnife.bind(this, view);

        rvMain = view.findViewById(R.id.rv_main);
        rvMain.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new SeriesRVAdapter(getContext(), this);
        rvMain.setAdapter(adapter);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvMain, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataReady(RestApiEvent.DataReadyEvent event) {
        adapter.setNewData(event.getAllList());
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

    @Override
    public void onTapProgram(String id) {
        Intent intent = ProgramDetailActivity.newIntent(getContext());
        intent.putExtra("CATEGORY", "CATEGORY");
        intent.putExtra("category_id", id);
        startActivity(intent);
    }

}
