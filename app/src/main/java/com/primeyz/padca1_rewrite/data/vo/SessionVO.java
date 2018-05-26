package com.primeyz.padca1_rewrite.data.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yepyaesonetun on 5/25/18.
 **/

public class SessionVO implements BaseVO{

    @SerializedName("session-id")
    private String sessionId;

    @SerializedName("title")
    private String title;

    @SerializedName("length-in-seconds")
    private Integer lengthInSeconds;

    @SerializedName("file-path")
    private String filePath;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLengthInSeconds() {
        return lengthInSeconds;
    }

    public void setLengthInSeconds(Integer lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
