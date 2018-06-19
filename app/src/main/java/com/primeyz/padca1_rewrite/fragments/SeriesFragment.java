package com.primeyz.padca1_rewrite.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.primeyz.padca1_rewrite.R;
import com.primeyz.padca1_rewrite.adapters.SeriesRVAdapter;
import com.primeyz.padca1_rewrite.data.model.SeriesModal;
import com.primeyz.padca1_rewrite.data.vo.BaseVO;
import com.primeyz.padca1_rewrite.delegates.ProgramDelegate;
import com.primeyz.padca1_rewrite.mvp.presenters.SeriesListFragPresenter;
import com.primeyz.padca1_rewrite.mvp.views.SeriesListFragView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yepyaesonetun on 5/19/18.
 **/

public class SeriesFragment extends Fragment implements SeriesListFragView {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    private SeriesRVAdapter adapter;
    private Context mContext;
    private ProgramDelegate delegate;

    private SeriesModal seriesModal;
    private List<BaseVO> baseVOList = new ArrayList<>();
    private SeriesListFragPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_series, container, false);
        ButterKnife.bind(this, view);
        mContext = getContext();

        mPresenter = new SeriesListFragPresenter(this);
        mPresenter.onCreate();

        seriesModal = SeriesModal.getObjInstance();
        seriesModal.initDatabase(getContext());

//        seriesModal.getCategories().observe(this, new Observer<List<CategoryVO>>() {
//            @Override
//            public void onChanged(@Nullable List<CategoryVO> categoriesVOs) {
//                baseVOList.addAll(categoriesVOs);
//            }
//        });
//        seriesModal.getCurrentPrograms().observe(this, new Observer<List<CurrentProgramVO>>() {
//            @Override
//            public void onChanged(@Nullable List<CurrentProgramVO> currentProgramsVOs) {
//                baseVOList.addAll(currentProgramsVOs);
//            }
//        });
//        seriesModal.getTopics().observe(this, new Observer<List<TopicVO>>() {
//            @Override
//            public void onChanged(@Nullable List<TopicVO> topicsVOs) {
//                baseVOList.addAll(topicsVOs);
//                adapter.setNewData(baseVOList);
//            }
//        });

        rvMain = view.findViewById(R.id.rv_main);
        rvMain.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new SeriesRVAdapter(getContext(), delegate);
        rvMain.setAdapter(adapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        delegate = (ProgramDelegate) context;
    }


    @Override
    public void displayErrorMsg(String errorMsg) {
        Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayData(List<BaseVO> baseVOList) {
        adapter.setNewData(baseVOList);
    }
}
