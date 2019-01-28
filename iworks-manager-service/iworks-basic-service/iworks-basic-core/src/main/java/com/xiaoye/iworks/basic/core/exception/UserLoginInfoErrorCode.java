package com.xiaoye.iworks.basic.core.exception;

import com.xiaoye.iworks.common.exception.annotation.Error;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;

/**
 * 功能描述: 【系统用户登录信息】异常编码类【36242220195】
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
public class UserLoginInfoErrorCode extends ServiceErrorCode {

    @Error(code = "3624222019501", desc = "系统用户登录信息数据查询异常")
    public static final String DATA_QRY_ERROR = "3624222019501";
    @Error(code = "3624222019502", desc = "系统用户登录信息数据已存在异常")
    public static final String DATA_EXIST_ERROR = "3624222019502";
    @Error(code = "3624222019503", desc = "系统用户登录信息数据不存在异常")
    public static final String DATA_UNEXIST_ERROR = "3624222019503";
    @Error(code = "3624222019504", desc = "系统用户登录信息数据新增异常")
    public static final String DATA_INSERT_ERROR = "3624222019504";
    @Error(code = "3624222019505", desc = "系统用户登录信息数据更新异常")
    public static final String DATA_UPDATE_ERROR = "3624222019505";
    @Error(code = "3624222019506", desc = "系统用户登录信息数据刪除异常")
    public static final String DATA_DELETE_ERROR = "3624222019506";
    @Error(code = "3624222019507", desc = "用户登录异常")
    public static final String USER_LOGIN_ERROR = "3624222019507";
    @Error(code = "3624222019508", desc = "用户登录信息被篡改异常")
    public static final String DATA_CHANGE_ERROR = "3624222019508";

    public static final Class getCurrentClass(){
        return new Object(){
            public Class getClassForStatic(){
                return this.getClass();
            }
        }.getClassForStatic();
    }
}