package com.dexingworld.hanfu.repository.entity;


import com.dexingworld.hanfu.repository.entity.api.BasePageDataObj;

/**
 * 
 */
public class News extends BasePageDataObj{
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
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", pic='" + pic + '\'' +
                ", link='" + link + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
