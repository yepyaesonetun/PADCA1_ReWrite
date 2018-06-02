package com.primeyz.padca1_rewrite.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yepyaesonetun on 5/25/18.
 **/

public class ProgramVO{
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

    public List<SessionVO> sessions;



    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Integer> getAverageLengths() {
        return averageLengths;
    }

    public void setAverageLengths(List<Integer> averageLengths) {
        this.averageLengths = averageLengths;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SessionVO> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionVO> sessions) {
        this.sessions = sessions;
    }
}
