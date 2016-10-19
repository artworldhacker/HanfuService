package com.dexingworld.hanfu.common.parameter;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by wangpeng on 2016/10/19.
 */
public class GroovyRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "调用方法不能为空")
    private String method;

    @NotBlank(message = "调用groovy代码不能为空")
    private String code;

    private Map<String,Object> map;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
