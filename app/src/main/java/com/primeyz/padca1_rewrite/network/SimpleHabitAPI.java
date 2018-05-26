package com.primeyz.padca1_rewrite.network;

import com.primeyz.padca1_rewrite.network.responses.GetCategoriesResponse;
import com.primeyz.padca1_rewrite.network.responses.GetCurrentProgramsResponse;
import com.primeyz.padca1_rewrite.network.responses.GetTopicResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public interface SimpleHabitAPI {

    @FormUrlEncoded
    @POST("getCurrentProgram.php")
    Call<GetCurrentProgramsResponse> loadCurrentPrograms(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken);

    @FormUrlEncoded
    @POST("getCategoriesPrograms.php")
    Call<GetCategoriesResponse> loadCategories(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken);

    @FormUrlEncoded
    @POST("getTopics.php")
    Call<GetTopicResponse> loadTopics(
            @Field("page") int pageIndex,
            @Field("access_token") String accessToken);
}
