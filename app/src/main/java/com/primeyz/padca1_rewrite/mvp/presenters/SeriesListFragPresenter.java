package com.primeyz.padca1_rewrite.mvp.presenters;

import android.util.Log;

import com.primeyz.padca1_rewrite.data.model.SeriesModal;
import com.primeyz.padca1_rewrite.delegates.ProgramDelegate;
import com.primeyz.padca1_rewrite.events.RestApiEvent;
import com.primeyz.padca1_rewrite.mvp.views.SeriesListFragView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by yepyaesonetun on 6/19/18.
 **/

public class SeriesListFragPresenter extends BasePresenter<SeriesListFragView> implements ProgramDelegate{

    private ProgramDelegate mDelegate;

    public SeriesListFragPresenter(SeriesListFragView mView) {
        super(mView);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SeriesModal.getObjInstance().startloadingSimpleHabit();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        mView.displayErrorMsg(event.getErrorMsg());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataReady(RestApiEvent.DataReadyEvent event) {
        Log.e("AA", "onDataReady: "+ event.getAllList());
        mView.displayData(event.getAllList());
    }

    @Override
    public void onTapCurrent() {
        mView.launchCurrentProgram();
    }

    @Override
    public void onTapProgram(String id) {
        mView.launchProgram(id);
    }
}
