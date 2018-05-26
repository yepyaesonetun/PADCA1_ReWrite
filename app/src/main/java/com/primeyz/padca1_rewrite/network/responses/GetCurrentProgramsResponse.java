package com.primeyz.padca1_rewrite.network.responses;

import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;

/**
 * Created by yepyaesonetun on 5/26/18.
 **/

public class GetCurrentProgramsResponse {

    private int code;
    private String message;
    private String apiVersion;
    private CurrentProgramVO currentProgram;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public CurrentProgramVO getCurrentProgram() {
        return currentProgram;
    }

    public void setCurrentProgram(CurrentProgramVO currentProgram) {
        this.currentProgram = currentProgram;
    }
}
