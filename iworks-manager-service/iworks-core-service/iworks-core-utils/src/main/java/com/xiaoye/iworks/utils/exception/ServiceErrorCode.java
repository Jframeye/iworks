package com.xiaoye.iworks.utils.exception;

/**
 * 功能描述: 异常错误码基类
 * @auther: yehl
 * @date: 2019/1/5 14:52
 */
public class ServiceErrorCode {

    public static final String APP_ERROR = "3624222019001"; // 系统异常
    public static final String PARAM_ERROR = "3624222019002"; // 参数异常
    public static final String SQL_ERROR = "3624222019003"; // 数据库异常
    public static final String INVALID_LOGIN_ERROR = "3624222019004"; // 登录失效
    public static final String EXPIRED_LOGIN_ERROR = "3624222019005"; // 登录过期
    public static final String NO_PERMISSION_ERROR = "3624222019006"; // 无权操作
}
