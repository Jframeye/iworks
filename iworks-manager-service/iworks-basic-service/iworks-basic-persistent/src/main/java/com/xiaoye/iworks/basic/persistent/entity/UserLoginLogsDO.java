package com.xiaoye.iworks.basic.persistent.entity;
import com.xiaoye.iworks.persistent.annotation.Column;
import com.xiaoye.iworks.persistent.annotation.Key;
import com.xiaoye.iworks.persistent.annotation.Table;
import com.xiaoye.iworks.persistent.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 实体类【系统用户登录日志】
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_user_login_logs", database = "basic")
public class UserLoginLogsDO extends Entity {
	private static final long serialVersionUID = 1L;
	@Key(name = "pkid", comments = "自增主键[auto_increment]")
    private Long pkid;
	@Column(name = "user_pkid", comments = "用户主键")
    private Long userPkid;
	@Column(name = "user_no", comments = "用户编号")
    private String userNo;
	@Column(name = "user_name", comments = "用户账号")
    private String userName;
	@Column(name = "ip", comments = "登陆IP")
    private String ip;
	@Column(name = "address", comments = "登陆地址")
    private String address;
	@Column(name = "browser", comments = "使用的浏览器")
    private String browser;
	@Column(name = "system", comments = "使用的操作系统")
    private String system;
	@Column(name = "message", comments = "登陆操作结果")
    private String message;
	@Column(name = "sign", comments = "数据签名")
    private String sign;
	@Column(name = "state", comments = "状态[1-成功；2-失败；]")
    private Integer state;
	@Column(name = "lstate", comments = "逻辑状态[1-正常；2-删除]")
    private Integer lstate;
	@Column(name = "create_time", comments = "创建时间")
    private Date createTime;
	@Column(name = "modify_time", comments = "最后修改时间")
    private Date modifyTime;
}