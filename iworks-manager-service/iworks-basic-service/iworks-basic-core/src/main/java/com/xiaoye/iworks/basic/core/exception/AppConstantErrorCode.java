package com.xiaoye.iworks.basic.core.exception;

import com.xiaoye.iworks.common.exception.annotation.Error;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;

/**
 * 功能描述: 系统常量异常编码【36242220191】
 * @auther: yehl
 * @date: 2019/1/5 14:55
 */
public class AppConstantErrorCode extends ServiceErrorCode {

    @Error(code = "3624222019101", desc = "系统常量数据查询异常")
    public static final String DATA_QRY_ERROR = "3624222019101";
    @Error(code = "3624222019102", desc = "系统常量数据统计异常")
    public static final String DATA_COUNT_ERROR = "3624222019102";
    @Error(code = "3624222019103", desc = "系统常量数据删除异常")
    public static final String DATA_DELETE_ERROR = "3624222019103";
    @Error(code = "3624222019104", desc = "系统常量数据更新异常")
    public static final String DATA_UPDATE_ERROR = "3624222019104";
    @Error(code = "3624222019105", desc = "系统常量数据插入异常")
    public static final String DATA_INSERT_ERROR = "3624222019105";
    @Error(code = "3624222019106", desc = "系统常量数据不存在异常")
    public static final String DATA_UNEXIST_ERROR = "3624222019106";
    @Error(code = "3624222019107", desc = "系统常量数据已存在异常")
    public static final String DATA_EXIST_ERROR = "3624222019107";

    public static final Class getCurrentClass(){
        return new Object(){
            public Class getClassForStatic(){
                return this.getClass();
            }
        }.getClassForStatic();
    }
}
