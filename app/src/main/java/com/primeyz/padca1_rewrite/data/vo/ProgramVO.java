package com.primeyz.padca1_rewrite.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yepyaesonetun on 5/25/18.
 **/

public class ProgramVO {
    @SerializedName("program-id")
    public String programId;

    @SerializedName("title")
    public String title;

    @SerializedName("image")
    public String image;

    @SerializedName("average-lengths")
    public List<Integer> averageLengths;

    @SerializedName("description")
    public String description;

    @SerializedName("sessions")
    public List<SessionVO> sessions;


}
