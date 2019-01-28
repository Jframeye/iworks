package com.xiaoye.iworks.basic.api.dto;
import com.xiaoye.iworks.api.input.Input;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 系统用户登录日志 查询入参
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginLogsDto implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 自增主键[auto_increment] **/
    private Long pkid;
    /** 用户主键 **/
    private Long userPkid;
    /** 用户编号 **/
    private String userNo;
    /** 用户账号 **/
    private String userName;
    /** 登陆IP **/
    private String ip;
    /** 登陆地址 **/
    private String address;
    /** 使用的浏览器 **/
    private String browser;
    /** 使用的操作系统 **/
    private String system;
    /** 登陆操作结果 **/
    private String message;
    /** 数据签名 **/
    private String sign;
    /** 状态[1-成功；2-失败；] **/
    private Integer state;
    /** 逻辑状态[1-正常；2-删除] **/
    private Integer lstate;
    /** 创建时间 **/
    private Date createTime;
    /** 最后修改时间 **/
    private Date modifyTime;
}