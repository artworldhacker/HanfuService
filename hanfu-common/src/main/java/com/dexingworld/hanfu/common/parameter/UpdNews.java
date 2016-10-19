package com.dexingworld.hanfu.common.parameter;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/10/10.
 */
public class UpdNews implements Serializable {

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
}
