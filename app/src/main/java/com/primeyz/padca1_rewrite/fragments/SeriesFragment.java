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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.adapters.AllTopicsAdapter;
import com.primeyz.padca1_rewrite.adapters.EveningMeditationsAdapter;
import com.primeyz.padca1_rewrite.adapters.HealthyMindAdapter;
import com.primeyz.padca1_rewrite.components.EmptyViewPod;
import com.primeyz.padca1_rewrite.data.model.SeriesModal;
import com.primeyz.padca1_rewrite.events.RestApiEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/19/18.
 **/

public class SeriesFragment extends Fragment {


    @BindView(R.id.tv_item_title)
    TextView tvCurrentProgramTitle;

    @BindView(R.id.tv_item_minute)
    TextView tvCurrentProgramMin;

    @BindView(R.id.textView_current_period)
    TextView tvCurrentPeriod;

    @BindView(R.id.emty_view)
    View emptyView;

    @BindView(R.id.main_view)
    LinearLayout mainView;

    RecyclerView rvEvening;
    RecyclerView rvHealthyMind;
    RecyclerView rvAllTopics;
    EveningMeditationsAdapter eveningAdapter;
    HealthyMindAdapter healthyMindAdapter;
    AllTopicsAdapter allTopicsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_series, container, false);
        ButterKnife.bind(this, view);
        emptyView.setVisibility(View.GONE);
        mainView.setVisibility(View.VISIBLE);

        SeriesModal.getObjInstance().startloadingSimpleHabit();

        rvEvening = view.findViewById(R.id.rv_healthy_min);
        rvEvening.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        eveningAdapter = new EveningMeditationsAdapter(getContext());
        rvEvening.setAdapter(eveningAdapter);

        rvHealthyMind = view.findViewById(R.id.rv_most_popular);
        rvHealthyMind.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        healthyMindAdapter = new HealthyMindAdapter(getContext());
        rvHealthyMind.setAdapter(healthyMindAdapter);

        rvAllTopics = view.findViewById(R.id.rv_all_topic);
        rvAllTopics.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        allTopicsAdapter = new AllTopicsAdapter(getContext());
        rvAllTopics.setAdapter(allTopicsAdapter);

        rvAllTopics.setNestedScrollingEnabled(false);
        rvEvening.setNestedScrollingEnabled(false);
        rvHealthyMind.setNestedScrollingEnabled(false);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCurrentProgramsDataLoaded(RestApiEvent.currentProgramDataLoadedEvent event) {

        if (event.getLoadedCurrentProgram() != null) {
            tvCurrentProgramTitle.setText(event.getLoadedCurrentProgram().getTitle());
            tvCurrentPeriod.setText(event.getLoadedCurrentProgram().getCurrentPeriod());
            tvCurrentProgramMin.setText(event.getLoadedCurrentProgram().getAverageLengths().get(0) + " mins");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCategoriesDataLoaded(RestApiEvent.CategoriesDataLoadedEvent event) {
        if (event.getLoadCategories() != null
                && event.getLoadCategories().size() > 0) {
            eveningAdapter.appendNewData(event.getLoadCategories().get(0).getPrograms());

            if (event.getLoadCategories().size() > 1) {
                healthyMindAdapter.appendNewData(event.getLoadCategories().get(1).getPrograms());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTopicsDataLoaded(RestApiEvent.TopicsDataLoadedEvent event) {
        if (event.getLoadTopics() != null && event.getLoadTopics().size() > 0) {
            allTopicsAdapter.appendNewData(event.getLoadTopics());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvEvening, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
        mainView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
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
