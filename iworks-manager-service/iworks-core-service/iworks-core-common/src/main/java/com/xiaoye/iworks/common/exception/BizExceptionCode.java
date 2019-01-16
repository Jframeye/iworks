package com.xiaoye.iworks.common.exception;

import com.xiaoye.iworks.common.exception.annotation.Error;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;

/**
 * 功能描述: 异常常量类
 * @auther: yehl
 * @date: 2019/1/2 18:23
 */
public class BizExceptionCode extends ServiceErrorCode {

    @Error(code = "3624222019001", desc = "系统异常")
    public static final String APP_ERROR = "3624222019001";
    @Error(code = "3624222019002", desc = "参数异常")
    public static final String PARAM_ERROR = "3624222019002";
    @Error(code = "3624222019003", desc = "数据库异常")
    public static final String SQL_ERROR = "3624222019003";
}
