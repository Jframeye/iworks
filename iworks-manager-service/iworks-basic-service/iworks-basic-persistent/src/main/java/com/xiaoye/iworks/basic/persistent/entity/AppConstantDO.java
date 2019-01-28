package com.xiaoye.iworks.basic.persistent.entity;
import com.xiaoye.iworks.persistent.annotation.Column;
import com.xiaoye.iworks.persistent.annotation.Key;
import com.xiaoye.iworks.persistent.annotation.Table;
import com.xiaoye.iworks.persistent.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 实体类【系统常量数据】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_app_constant", database = "basic")
public class AppConstantDO extends Entity {
	private static final long serialVersionUID = 1L;
	@Key(name = "pkid", comments = "自增主键[auto_increment]")
    private Long pkid;
	@Column(name = "constant_key", comments = "常量编码")
    private String constantKey;
	@Column(name = "constant_value", comments = "常量值")
    private String constantValue;
	@Column(name = "constant_desc", comments = "常量说明")
    private String constantDesc;
	@Column(name = "sign", comments = "数据签名")
    private String sign;
	@Column(name = "tag_type", comments = "标签类型[success,info,warning,danger]")
    private String tagType;
	@Column(name = "state", comments = "状态[1-可用；2-禁用]")
    private Integer state;
	@Column(name = "lstate", comments = "逻辑状态[1-正常；2-删除]")
    private Integer lstate;
	@Column(name = "create_by", comments = "创建人")
    private String createBy;
	@Column(name = "create_time", comments = "创建时间")
    private Date createTime;
	@Column(name = "modify_by", comments = "最后修改人")
    private String modifyBy;
	@Column(name = "modify_time", comments = "最后修改时间")
    private Date modifyTime;
}