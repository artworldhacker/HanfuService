package com.dexingworld.hanfu.common.response;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by wangpeng on 2016/10/11.
 */
public class BaseResponse implements Serializable {

    private static final String DEFUAL_ERROR_MSG = "系统错误";

    private boolean status;

    private String error;

    private Map<String, String> errorMap;

    private String statusCode;

    public BaseResponse makeFailure(String errorMsg){
        this.status = false;
        this.error = errorMsg;
        return this;
    }

    public BaseResponse makeSuccessful(){
        this.status = true;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
