package com.xiaoye.iworks.basic.core.exception;
import com.xiaoye.iworks.common.exception.annotation.Error;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;

/**
 * 功能描述: 【用户基本信息】异常编码类【36242220197】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
public class UserBasicInfoErrorCode extends ServiceErrorCode {
    @Error(code = "3624222019701", desc = "用户基本信息数据查询异常")
    public static final String DATA_QRY_ERROR = "3624222019701";
    @Error(code = "3624222019702", desc = "用户基本信息数据已存在异常")
    public static final String DATA_EXIST_ERROR = "3624222019702";
    @Error(code = "3624222019703", desc = "用户基本信息数据不存在异常")
    public static final String DATA_UNEXIST_ERROR = "3624222019703";
    @Error(code = "3624222019704", desc = "用户基本信息数据新增异常")
    public static final String DATA_INSERT_ERROR = "3624222019704";
    @Error(code = "3624222019705", desc = "用户基本信息数据更新异常")
    public static final String DATA_UPDATE_ERROR = "3624222019705";
    @Error(code = "3624222019706", desc = "用户基本信息数据刪除异常")
    public static final String DATA_DELETE_ERROR = "3624222019706";

    public static final Class getCurrentClass(){
        return new Object(){
            public Class getClassForStatic(){
                return this.getClass();
            }
        }.getClassForStatic();
    }
}