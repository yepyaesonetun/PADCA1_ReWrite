package com.primeyz.padca1_rewrite.network;

import com.google.gson.Gson;
import com.primeyz.padca1_rewrite.events.RestApiEvent;
import com.primeyz.padca1_rewrite.network.responses.GetCategoriesResponse;
import com.primeyz.padca1_rewrite.network.responses.GetCurrentProgramsResponse;
import com.primeyz.padca1_rewrite.network.responses.GetTopicResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.primeyz.padca1_rewrite.utils.AppConstants.BASE_URL;

/**
 * Created by yepyaesonetun on 5/25/18.
 **/

public class SimpleHabitsDataAgentImpl implements SimpleHabitsDataAgent {

    private static SimpleHabitsDataAgentImpl objInstance;

    private SimpleHabitAPI theAPI;

    private SimpleHabitsDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(SimpleHabitAPI.class);

    }


    public static SimpleHabitsDataAgentImpl getNewInstance() {
        if (objInstance == null) {
            objInstance = new SimpleHabitsDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadCurrentPrograms(String token, int pageNo) {
        Call<GetCurrentProgramsResponse> currentProgramsResponseCall = theAPI.loadCurrentPrograms(pageNo, token);
        currentProgramsResponseCall.enqueue(new Callback<GetCurrentProgramsResponse>() {
            @Override
            public void onResponse(Call<GetCurrentProgramsResponse> call, Response<GetCurrentProgramsResponse> response) {
                GetCurrentProgramsResponse resultResponse = response.body();
                if (resultResponse != null
                        && resultResponse.getCurrentProgram() != null) {
                    // Invoke EventBus
                    RestApiEvent.currentProgramDataLoadedEvent currentProgramDataLoadedEvent =
                            new RestApiEvent.currentProgramDataLoadedEvent(resultResponse.getCurrentProgram());
                    EventBus.getDefault().post(currentProgramDataLoadedEvent);
                } else {
                    // Invoke EventBus Error
                    RestApiEvent.ErrorInvokingAPIEvent errorEvent =
                            new RestApiEvent.ErrorInvokingAPIEvent("Current Program Couldn't be loaded.");
                    EventBus.getDefault().post(errorEvent);
                }
            }

            @Override
            public void onFailure(Call<GetCurrentProgramsResponse> call, Throwable t) {
                RestApiEvent.ErrorInvokingAPIEvent errorEvent = new RestApiEvent.ErrorInvokingAPIEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }

    @Override
    public void loadCategories(String token, int pageNo) {
        Call<GetCategoriesResponse> loadCategoriesCall = theAPI.loadCategories(pageNo, token);
        loadCategoriesCall.enqueue(new Callback<GetCategoriesResponse>() {
            @Override
            public void onResponse(Call<GetCategoriesResponse> call, Response<GetCategoriesResponse> response) {
                GetCategoriesResponse getCategoriesResponse = response.body();
                if (getCategoriesResponse != null
                        && getCategoriesResponse.getCategoriesPrograms().size() > 0) {
                    RestApiEvent.CategoriesDataLoadedEvent categoriesDataLoadedEvent =
                            new RestApiEvent.CategoriesDataLoadedEvent(
                                    getCategoriesResponse.getCategoriesPrograms());
                    EventBus.getDefault().post(categoriesDataLoadedEvent);
                } else {
                    RestApiEvent.ErrorInvokingAPIEvent errorEvent =
                            new RestApiEvent.ErrorInvokingAPIEvent("Categories couldn't be loaded.");
                    EventBus.getDefault().post(errorEvent);
                }
            }

            @Override
            public void onFailure(Call<GetCategoriesResponse> call, Throwable t) {
                RestApiEvent.ErrorInvokingAPIEvent errorEvent = new RestApiEvent.ErrorInvokingAPIEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }

    @Override
    public void loadTopic(String token, int pageNo) {
        Call<GetTopicResponse> loadTopicsCall = theAPI.loadTopics(pageNo, token);
        loadTopicsCall.enqueue(new Callback<GetTopicResponse>() {
            @Override
            public void onResponse(Call<GetTopicResponse> call, Response<GetTopicResponse> response) {
                GetTopicResponse getTopicResponse = response.body();
                if (getTopicResponse != null
                        && getTopicResponse.getTopics().size() > 0) {
                    RestApiEvent.TopicsDataLoadedEvent topicsDataLoadedEvent =
                            new RestApiEvent.TopicsDataLoadedEvent(
                                    getTopicResponse.getTopics());
                    EventBus.getDefault().post(topicsDataLoadedEvent);
                } else {
                    RestApiEvent.ErrorInvokingAPIEvent errorEvent =
                            new RestApiEvent.ErrorInvokingAPIEvent("Topic Couldn't be loaded.");
                    EventBus.getDefault().post(errorEvent);
                }
            }

            @Override
            public void onFailure(Call<GetTopicResponse> call, Throwable t) {
                RestApiEvent.ErrorInvokingAPIEvent errorEvent = new RestApiEvent.ErrorInvokingAPIEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }
}
