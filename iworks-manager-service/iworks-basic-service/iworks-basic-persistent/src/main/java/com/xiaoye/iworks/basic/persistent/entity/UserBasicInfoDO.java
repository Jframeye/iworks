package com.xiaoye.iworks.basic.persistent.entity;

import com.xiaoye.iworks.persistent.annotation.Column;
import com.xiaoye.iworks.persistent.annotation.Key;
import com.xiaoye.iworks.persistent.annotation.Sign;
import com.xiaoye.iworks.persistent.annotation.Table;
import com.xiaoye.iworks.persistent.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 实体类【用户基本信息】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_user_basic_info", database = "basic")
public class UserBasicInfoDO extends Entity {
	private static final long serialVersionUID = 1L;

	@Sign
	@Key(name = "pkid", comments = "自增主键[auto_increment]")
    private Long pkid;
	@Sign
	@Column(name = "user_pkid", comments = "用户主键")
    private Long userPkid;
	@Sign
	@Column(name = "user_no", comments = "用户编号")
    private String userNo;
	@Column(name = "nick_name", comments = "用户名称")
    private String nickName;
	@Column(name = "sex", comments = "性别")
    private String sex;
	@Column(name = "age", comments = "年龄")
    private String age;
	@Column(name = "birthday", comments = "生日")
    private String birthday;
	@Sign
	@Column(name = "mobile", comments = "手机号码")
    private String mobile;
	@Sign
	@Column(name = "email", comments = "邮箱账户")
    private String email;
	@Column(name = "sign", comments = "数据签名")
    private String sign;
	@Column(name = "state", comments = "状态[1-正常；2-冻结；3-锁定；4-注销]")
    private Integer state;
	@Column(name = "lstate", comments = "逻辑状态[1-正常；2-删除]")
    private Integer lstate;
	@Sign
	@Column(name = "create_time", comments = "创建时间")
    private Date createTime;
	@Column(name = "modify_time", comments = "最后修改时间")
    private Date modifyTime;
}