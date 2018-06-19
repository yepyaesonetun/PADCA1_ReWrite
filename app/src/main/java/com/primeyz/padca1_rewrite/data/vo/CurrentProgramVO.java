package com.primeyz.padca1_rewrite.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.primeyz.padca1_rewrite.data.db.ProgramLengthTypeConverter;

import java.util.List;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

@Entity(tableName = "CurrentProgram")
public class CurrentProgramVO implements BaseVO{

    @PrimaryKey
    @SerializedName("program-id")
    private @NonNull String programId;

    @SerializedName("title")
    private String title;
    @SerializedName("current-period")
    private String currentPeriod;
    @SerializedName("background")
    private String background;
    @SerializedName("description")
    private String description;
    @SerializedName("average-lengths")
    @TypeConverters(ProgramLengthTypeConverter.class)
    private int[] averageLengths;

    @SerializedName("sessions")
    @Ignore
    private List<SessionVO> sessionVOList;

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

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

    public String getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(String currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getAverageLengths() {
        return averageLengths;
    }

    public void setAverageLengths(int[] averageLengths) {
        this.averageLengths = averageLengths;
    }

    public List<SessionVO> getSessionVOList() {
        return sessionVOList;
    }

    public void setSessionVOList(List<SessionVO> sessionVOList) {
        this.sessionVOList = sessionVOList;
    }
}
