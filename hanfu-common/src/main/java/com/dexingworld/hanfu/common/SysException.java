package com.dexingworld.hanfu.common;

/**
 * 自定义系统异常类
 * Created by wangpeng on 2016/10/14.
 */
public class SysException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 异常消息
    private static final String message = "";

    private Integer errCode = -1;

    /**
     * 无参构造函数
     *
     * @return ApplicationException 用户未登录异常类的实例
     */
    public SysException() {
        super(message);
    }

    /**
     * 构造函数
     *
     * @param message 异常消息描述
     * @return ApplicationException 用户未登录异常类的实例
     */
    public SysException(String message) {
        super(message);
    }

    public SysException(String message, Integer errCode) {
        super(message);
        this.errCode = errCode;
    }

    /**
     * 构造函数
     *
     * @param cause 异常原因
     * @return ApplicationException 用户未登录异常类的实例
     */
    public SysException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数
     *
     * @param message 异常消息
     * @param cause   异常原因
     * @return ApplicationException 用户未登录异常类的实例
     */
    public SysException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }
}
