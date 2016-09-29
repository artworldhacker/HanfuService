package com.dexingworld.hanfu.web.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class PageResponse<T> implements java.io.Serializable {
    private boolean status;

    private String error;

    private int total;

    private int current;

    private int pageSize;

    private List<T> rows;

    private Map<String, String> errorMap = new HashMap<String, String>();
    
    public PageResponse() {

    }

    public PageResponse(boolean status, String error) {
	this.status = status;
	this.error = error;
    }

    public PageResponse(boolean status, int count, int current, int pageSize,
	    List<T> rows) {
	this.status = status;
	this.total = count;
	this.current = current;
	this.pageSize = pageSize;
	this.rows = rows;
    }

    public PageResponse(boolean status, String error, int total, int current,
	    int pageSize, List<T> rows) {
	this.status = status;
	this.error = error;
	this.total = total;
	this.current = current;
	this.pageSize = pageSize;
	this.rows = rows;
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

    public int getTotal() {
	return total;
    }

    public void setTotal(int total) {
	this.total = total;
    }

    public int getCurrent() {
	return current;
    }

    public void setCurrent(int current) {
	this.current = current;
    }

    public int getPageSize() {
	return pageSize;
    }

    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    public List<T> getRows() {
	return rows;
    }

    public void setRows(List<T> rows) {
	this.rows = rows;
    }

	public Map<String, String> getErrorMap() {
	return errorMap;
	}

	public void setErrorMap(Map<String, String> errorMap) {
	this.errorMap = errorMap;
	}

}
