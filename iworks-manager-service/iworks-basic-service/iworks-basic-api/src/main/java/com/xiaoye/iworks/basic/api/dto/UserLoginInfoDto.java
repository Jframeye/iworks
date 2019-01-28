package com.xiaoye.iworks.basic.api.dto;

import com.xiaoye.iworks.api.input.Input;
import java.io.Serializable;
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
public class UserLoginInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 自增主键[auto_increment] **/
    private Long pkid;
    /** 用户编号 **/
    private String userNo;
    /** 用户昵称 **/
    private String nickName;
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
    /** 登录错误次数[密码输入错误次数] **/
    private Integer errorCount;
    /** 账户锁定时间[账户锁定时间] **/
    private Date lockTime;
    /** 数据签名 **/
    private String sign;
    /** 状态[1-正常；2-冻结；3-锁定；4-注销] **/
    private Integer state;
    /** 创建时间 **/
    private Date createTime;
    /** 最后修改时间 **/
    private Date modifyTime;
}