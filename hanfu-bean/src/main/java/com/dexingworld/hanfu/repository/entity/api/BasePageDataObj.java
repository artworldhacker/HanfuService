package com.dexingworld.hanfu.repository.entity.api;

/**
 * Created by wangpeng on 2016/10/10.
 */
public class BasePageDataObj extends PageLimitDataObj {
    private static final long serialVersionUID = 1L;
    private Object extendedParameter;

    public Object getExtendedParameter() {
        return extendedParameter;
    }

    public void setExtendedParameter(Object extendedParameter) {
        this.extendedParameter = extendedParameter;
    }
}
