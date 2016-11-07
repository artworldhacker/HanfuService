package com.dexingworld.hanfu.common.parameter;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/10/10.
 */
public class QueryNews extends SessionParameter {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 图片(Base64)
     */
    private String pic;
    /**
     * 链接
     */
    private String link;
    /**
     * 备注
     */
    private String remark;

    private Object extendedParameter;

    /**
     * 开始条数
     */
    private Integer startRow;


    /**
     * 结束条数
     */
    private Integer endRow;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getExtendedParameter() {
        return extendedParameter;
    }

    public void setExtendedParameter(Object extendedParameter) {
        this.extendedParameter = extendedParameter;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }
}
