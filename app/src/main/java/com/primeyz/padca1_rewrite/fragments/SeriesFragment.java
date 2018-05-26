package com.primeyz.padca1_rewrite.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.adapters.AllTopicsAdapter;
import com.primeyz.padca1_rewrite.adapters.EveningMeditationsAdapter;
import com.primeyz.padca1_rewrite.adapters.HealthyMindAdapter;
import com.primeyz.padca1_rewrite.adapters.SeriesRVAdapter;
import com.primeyz.padca1_rewrite.components.EmptyViewPod;
import com.primeyz.padca1_rewrite.data.model.SeriesModal;
import com.primeyz.padca1_rewrite.data.vo.BaseVO;
import com.primeyz.padca1_rewrite.data.vo.CategoryVO;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;
import com.primeyz.padca1_rewrite.data.vo.TopicVO;
import com.primeyz.padca1_rewrite.events.RestApiEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/19/18.
 **/

public class SeriesFragment extends Fragment {


    @BindView(R.id.rv_main)
    RecyclerView rvMain;


    private SeriesRVAdapter adapter;
    private List<BaseVO> objList = new ArrayList<>();
    private List<CategoryVO> programVOList = new ArrayList<>();
    private List<TopicVO> topicItemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_series, container, false);
        ButterKnife.bind(this, view);

        rvMain = view.findViewById(R.id.rv_main);
        rvMain.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new SeriesRVAdapter(getContext());
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
}
