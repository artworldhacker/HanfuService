package com.dexingworld.hanfu.common.parameter;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/10/10.
 */
public class UpdNews extends SessionParameter{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotEmpty(message = "id不能为空")
    private Long id;
    /**
     * 标题
     */
    @NotBlank(message = "title不能为空")
    private String title;
    /**
     * 内容
     */
    @NotBlank(message = "content不能为空")
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
}
