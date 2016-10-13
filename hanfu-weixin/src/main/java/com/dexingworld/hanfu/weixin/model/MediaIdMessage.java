package com.dexingworld.hanfu.weixin.model;

import com.dexingworld.hanfu.weixin.annotation.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/10/13.
 */
public class MediaIdMessage implements Serializable {

    @XStreamAlias("MediaId")
    @XStreamCDATA
    private String MediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
