package com.primeyz.padca1_rewrite.data.model;

import com.primeyz.padca1_rewrite.data.vo.BaseVO;
import com.primeyz.padca1_rewrite.data.vo.CategoryVO;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.TopicVO;
import com.primeyz.padca1_rewrite.events.RestApiEvent;
import com.primeyz.padca1_rewrite.network.SimpleHabitsDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static com.primeyz.padca1_rewrite.utils.AppConstants.ACCESS_TOKEN;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public class SeriesModal {
    private static SeriesModal objInstance;

    private int mPageIndex = 1;

    List<BaseVO> list = new ArrayList<>();

    private SeriesModal() {
        EventBus.getDefault().register(this);
    }

    public static SeriesModal getObjInstance() {
        if (objInstance == null) {
            objInstance = new SeriesModal();
        }
        return objInstance;
    }

    public void startloadingSimpleHabit() {
        SimpleHabitsDataAgentImpl.getNewInstance().loadCurrentPrograms(ACCESS_TOKEN, mPageIndex);
    }

    @Subscribe
    public void onCurrentProgramDataLoaded(RestApiEvent.currentProgramDataLoadedEvent event) {
        list.add(event.getLoadedCurrentProgram());
        SimpleHabitsDataAgentImpl.getNewInstance().loadCategories(ACCESS_TOKEN, mPageIndex);
    }

    @Subscribe
    public void onCategoriesDataLoaded(RestApiEvent.CategoriesDataLoadedEvent event) {
        for (CategoryVO categoryVO: event.getLoadCategories()) {
            list.add(categoryVO);
        }
        SimpleHabitsDataAgentImpl.getNewInstance().loadTopic(ACCESS_TOKEN, mPageIndex);
    }

    @Subscribe
    public void onTopicsDataLoaded(RestApiEvent.TopicsDataLoadedEvent event) {
        list.addAll(event.getLoadTopics());

        RestApiEvent.DataReadyEvent dataReadyEvent = new RestApiEvent.DataReadyEvent(list);
        EventBus.getDefault().post(dataReadyEvent);
    }


}
