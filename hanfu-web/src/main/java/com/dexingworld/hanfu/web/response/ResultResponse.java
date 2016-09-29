package com.dexingworld.hanfu.web.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResultResponse<T> {

    private static final String DEFUAL_ERROR_MSG = "系统错误";

    private boolean status;

    private String error;

    private T result;

    private Map<String, String> errorMap;

    private String statusCode;

    public ResultResponse() {
    }
    public ResultResponse makeFailure(){
        this.status = false;
        this.error = DEFUAL_ERROR_MSG;
        return this;
    }

    public ResultResponse makeFailure(String errorMsg){
        this.status = false;
        this.error = errorMsg;
        return this;
    }

    public ResultResponse makeSuccessful(){
        this.status = true;
        return this;
    }

    public ResultResponse(boolean status) {
	this.status = status;
    }

    public ResultResponse(boolean status, String error) {
	this.status = status;
	this.error = error;
    }

    public ResultResponse(boolean status, String error, List<ObjectError> fes) {
	this.status = status;
	this.error = error;
	this.add(fes);
    }

    public ResultResponse(boolean status, T result) {
	super();
	this.status = status;
	this.result = result;
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

    public T getResult() {
	return result;
    }

    public void setResult(T result) {
	this.result = result;
    }

    public String getStatusCode() {
	return statusCode;
    }

    public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
    }

    public void add(List<ObjectError> fes) {
	if (errorMap == null) {
	    errorMap = new HashMap<String, String>();
	}
	for (ObjectError fe : fes) {
	    if (fe instanceof FieldError) {
		FieldError ferror = (FieldError) fe;
		errorMap.put(ferror.getField(), ferror.getDefaultMessage());
	    } else if (fe instanceof ObjectError) {
		errorMap.put(fe.getCode(), fe.getDefaultMessage());
	    }
	}
    }

    @Override
    public String toString() {
	final StringBuffer buffer = new StringBuffer();
	buffer.append(getClass().getName()).append(" [");
	buffer.append("status").append("='").append(isStatus()).append("' ");
	buffer.append("error").append("='").append(getError()).append("' ");
	buffer.append("result").append("='").append(getResult()).append("' ");
	return buffer.toString();
    }

}
