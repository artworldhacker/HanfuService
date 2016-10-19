package com.dexingworld.hanfu.middleware.groovy;

import groovy.lang.GroovyObject;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/10/19.
 */
public class GroovyObj implements Serializable {

    private GroovyObject groovyObject;

    private String version;

    public GroovyObject getGroovyObject() {
        return groovyObject;
    }

    public void setGroovyObject(GroovyObject groovyObject) {
        this.groovyObject = groovyObject;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
