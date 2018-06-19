package com.primeyz.padca1_rewrite.data.model;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.primeyz.padca1_rewrite.data.db.AppDatabase;
import com.primeyz.padca1_rewrite.data.vo.BaseVO;
import com.primeyz.padca1_rewrite.data.vo.CategoryVO;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;
import com.primeyz.padca1_rewrite.data.vo.SessionVO;
import com.primeyz.padca1_rewrite.data.vo.TopicVO;
import com.primeyz.padca1_rewrite.events.RestApiEvent;
import com.primeyz.padca1_rewrite.network.SimpleHabitAPI;
import com.primeyz.padca1_rewrite.network.SimpleHabitsDataAgentImpl;
import com.primeyz.padca1_rewrite.network.responses.GetCategoriesResponse;
import com.primeyz.padca1_rewrite.network.responses.GetCurrentProgramsResponse;
import com.primeyz.padca1_rewrite.network.responses.GetTopicResponse;
import com.primeyz.padca1_rewrite.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.primeyz.padca1_rewrite.utils.AppConstants.ACCESS_TOKEN;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public class SeriesModal {

    private static SeriesModal objInstance;

    private static AppDatabase mAppDatabase;

    private LiveData<List<CategoryVO>> categories;

    private LiveData<List<CurrentProgramVO>> currentPrograms;

    private LiveData<List<TopicVO>> topics;



    private List<CategoryVO> categoryList;
    List<BaseVO> list = new ArrayList<>();
    private CurrentProgramVO currentProgramVO;

    private int mPageIndex = 1;

    private SeriesModal() {
        EventBus.getDefault().register(this);
    }

    public static SeriesModal getObjInstance() {
        if (objInstance == null) {
            objInstance = new SeriesModal();
        }
        return objInstance;
    }

    public void initDatabase(Context context) {
        mAppDatabase = AppDatabase.getNewsDatabase(context);
    }

    public LiveData<List<CategoryVO>> getCategories() {
        return mAppDatabase.categoriesDao().getAllCategories();
    }

    public LiveData<List<CurrentProgramVO>> getCurrentPrograms() {
        return mAppDatabase.currentProgramDao().getAllCurrentPrograms();
    }

    public LiveData<List<TopicVO>> getTopics() {
        return mAppDatabase.topicDao().getAllTopics();
    }

    public void loadCurrentData() {
        EventBus.getDefault().post(new RestApiEvent.currentProgramDataLoadedEvent(currentProgramVO));
    }

    public void loadProgramData(String programId) {
        ProgramVO programVO = new ProgramVO(programId);
        for (CategoryVO vo : categoryList) {
            for (ProgramVO voprogram : vo.getPrograms()) {
                if (voprogram.getProgramId().equalsIgnoreCase(programId)) {
                    programVO = voprogram;
                }
            }
        }
        EventBus.getDefault().post(new RestApiEvent.ProgramEvent(programVO));

    }

    public void startloadingSimpleHabit() {
        SimpleHabitsDataAgentImpl.getNewInstance().loadCurrentPrograms(ACCESS_TOKEN, mPageIndex);
    }


    @Subscribe
    public void onCurrentProgramDataLoaded(RestApiEvent.currentProgramDataLoadedEvent event) {
        list.add(event.getLoadedCurrentProgram());

        // ROOM
        CurrentProgramVO currentProgram = event.getLoadedCurrentProgram();
        for (SessionVO session : currentProgram.getSessionVOList()) {
            mAppDatabase.sessionDao().insertSession(session);

            currentProgram.setSessionId(session.getSessionId());
            mAppDatabase.currentProgramDao().insertCurrentProgram(currentProgram);
        }
        // ROOM

        SimpleHabitsDataAgentImpl.getNewInstance().loadCategories(ACCESS_TOKEN, mPageIndex);
    }

    @Subscribe
    public void onCategoriesDataLoaded(RestApiEvent.CategoriesDataLoadedEvent event) {
        for (CategoryVO categoryVO : event.getLoadCategories()) {
            list.add(categoryVO);
        }

        // ROOM
        List<CategoryVO> loadedCategories = event.getLoadCategories();
        for (CategoryVO category : loadedCategories) {

            for (ProgramVO program : category.getPrograms()) {

                for (SessionVO session : program.getSessions()) {
                    mAppDatabase.sessionDao().insertSession(session);

                    program.setSessionId(session.getSessionId());
                    mAppDatabase.programDao().insertProgram(program);
                }

                category.setProgramId(program.getProgramId());
                mAppDatabase.categoriesDao().insertCategory(category);

            }

        }
        // ROOM

        SimpleHabitsDataAgentImpl.getNewInstance().loadTopic(ACCESS_TOKEN, mPageIndex);
        categoryList = event.getLoadCategories();

    }

    @Subscribe
    public void onTopicsDataLoaded(RestApiEvent.TopicsDataLoadedEvent event) {
        list.addAll(event.getLoadTopics());

        // ROOM
        mAppDatabase.topicDao().insertTopics(event.getLoadTopics());
        // ROOM

        RestApiEvent.DataReadyEvent dataReadyEvent = new RestApiEvent.DataReadyEvent(list);
        EventBus.getDefault().post(dataReadyEvent);
    }

    public CurrentProgramVO getCurrentProgramVO() {
        for (BaseVO baseVO : list) {
            if (baseVO instanceof CurrentProgramVO) {
                Log.e("CC", "getCurrentProgramVO: " + ((CurrentProgramVO) baseVO).getTitle());
                return (CurrentProgramVO) baseVO;
            }
        }
        return null;
    }

    public ProgramVO getProgramVO(String id) {
        for (CategoryVO vo : categoryList) {
            for (ProgramVO programVO : vo.getPrograms()) {
                if (programVO.getProgramId().equalsIgnoreCase(id)) {
                    return programVO;
                }
            }
        }
        return null;
    }

}
