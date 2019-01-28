package com.xiaoye.iworks.basic.request;

import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 系统用户登录信息 数据查询入参
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginInfoQueryRequest extends Input {
    private static final long serialVersionUID = 1L;

    /** 登录账户 **/
    private String user_name;
    /** 登录密码 **/
    private String pass_word;
}