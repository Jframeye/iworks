package com.xiaoye.iworks.basic.api.input;

import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 功能描述: 系统用户登录信息 查询入参
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginInfoQueryInput extends Input {
    private static final long serialVersionUID = 1L;

    /** 自增主键[auto_increment] **/
    private Long pkid;
    /** 用户编号 **/
    private String userNo;
    /** 用户账号 **/
    private String userName;
    /** 用户密码 **/
    private String password;
    /** 密码盐 **/
    private String passsalt;
    /** 手机号码 **/
    private String mobile;
    /** 邮箱账号 **/
    private String email;
    /** 状态[1-正常；2-冻结；3-锁定；4-注销] **/
    private Integer state;
    /** 创建时间 **/
    private Date createTime;
    /** 最后修改时间 **/
    private Date modifyTime;
}