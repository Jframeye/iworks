package com.xiaoye.iworks.basic.request;

import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 系统用户登录信息 数据更新入参
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginInfoUpdateRequest extends Input {
    private static final long serialVersionUID = 1L;

    /** 用户昵称 **/
    private String nick_name;
    /** 用户账号 **/
    private String user_name;
    /** 用户密码 **/
    private String pass_word;
    /** 密码确认 **/
    private String confirm_pass;
    /** 用户邮箱 **/
    private String email;
    /** 用户手机 **/
    private String mobile;
}