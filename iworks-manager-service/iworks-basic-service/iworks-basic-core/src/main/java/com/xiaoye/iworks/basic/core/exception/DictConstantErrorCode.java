package com.xiaoye.iworks.basic.core.exception;

import com.xiaoye.iworks.common.exception.annotation.Error;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;

/**
 * 功能描述: 字典常量异常编码【36242220192】
 * @auther: yehl
 * @date: 2019/1/5 14:55
 */
public class DictConstantErrorCode extends ServiceErrorCode {

    @Error(code = "3624222019201", desc = "字典常量数据查询异常")
    public static final String DATA_QRY_ERROR = "3624222019201";
    @Error(code = "3624222019202", desc = "字典常量数据已存在异常")
    public static final String DATA_EXIST_ERROR = "3624222019202";
    @Error(code = "3624222019203", desc = "字典常量数据不存在异常")
    public static final String DATA_UNEXIST_ERROR = "3624222019203";
    @Error(code = "3624222019204", desc = "字典常量数据新增异常")
    public static final String DATA_INSERT_ERROR = "3624222019204";
    @Error(code = "3624222019205", desc = "字典常量数据更新异常")
    public static final String DATA_UPDATE_ERROR = "3624222019205";
    @Error(code = "3624222019206", desc = "字典常量数据刪除异常")
    public static final String DATA_DELETE_ERROR = "3624222019206";

    public static final Class getCurrentClass(){
        return new Object(){
            public Class getClassForStatic(){
                return this.getClass();
            }
        }.getClassForStatic();
    }
}
