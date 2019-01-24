package com.xiaoye.iworks.basic.persistent.entity;
import com.xiaoye.iworks.persistent.annotation.Column;
import com.xiaoye.iworks.persistent.annotation.Key;
import com.xiaoye.iworks.persistent.annotation.Table;
import com.xiaoye.iworks.persistent.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 实体类【系统用户登录信息】
 * @auther: auto create by python 
 * @date: 2019-01-24 17:38:50 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_user_login_info", database = "basic")
public class UserLoginInfoDO extends Entity {
	private static final long serialVersionUID = 1L;
	@Key(name = "pkid", comments = "自增主键[auto_increment]")
    private Long pkid;
	@Column(name = "user_no", comments = "用户编号")
    private String userNo;
	@Column(name = "user_name", comments = "用户账号")
    private String userName;
	@Column(name = "password", comments = "用户密码")
    private String password;
	@Column(name = "passsalt", comments = "密码盐")
    private String passsalt;
	@Column(name = "mobile", comments = "手机号码")
    private String mobile;
	@Column(name = "email", comments = "邮箱账号")
    private String email;
	@Column(name = "error_count", comments = "登录错误次数[密码输入错误次数]")
    private Integer errorCount;
	@Column(name = "lock_time", comments = "账户锁定时间[账户锁定时间]")
    private Date lockTime;
	@Column(name = "sign", comments = "数据签名")
    private String sign;
	@Column(name = "state", comments = "状态[1-正常；2-冻结；3-锁定；4-注销]")
    private Integer state;
	@Column(name = "lstate", comments = "逻辑状态[1-正常；2-删除]")
    private Integer lstate;
	@Column(name = "create_time", comments = "创建时间")
    private Date createTime;
	@Column(name = "modify_time", comments = "最后修改时间")
    private Date modifyTime;
}