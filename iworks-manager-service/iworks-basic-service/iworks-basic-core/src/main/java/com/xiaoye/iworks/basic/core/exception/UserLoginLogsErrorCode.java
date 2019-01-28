package com.xiaoye.iworks.basic.core.exception;
import com.xiaoye.iworks.common.exception.annotation.Error;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;

/**
 * 功能描述: 【系统用户登录日志】异常编码类【36242220196】
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
public class UserLoginLogsErrorCode extends ServiceErrorCode {
    @Error(code = "3624222019601", desc = "系统用户登录日志数据查询异常")
    public static final String DATA_QRY_ERROR = "3624222019601";
    @Error(code = "3624222019602", desc = "系统用户登录日志数据已存在异常")
    public static final String DATA_EXIST_ERROR = "3624222019602";
    @Error(code = "3624222019603", desc = "系统用户登录日志数据不存在异常")
    public static final String DATA_UNEXIST_ERROR = "3624222019603";
    @Error(code = "3624222019604", desc = "系统用户登录日志数据新增异常")
    public static final String DATA_INSERT_ERROR = "3624222019604";
    @Error(code = "3624222019605", desc = "系统用户登录日志数据更新异常")
    public static final String DATA_UPDATE_ERROR = "3624222019605";
    @Error(code = "3624222019606", desc = "系统用户登录日志数据刪除异常")
    public static final String DATA_DELETE_ERROR = "3624222019606";

    public static final Class getCurrentClass(){
        return new Object(){
            public Class getClassForStatic(){
                return this.getClass();
            }
        }.getClassForStatic();
    }
}