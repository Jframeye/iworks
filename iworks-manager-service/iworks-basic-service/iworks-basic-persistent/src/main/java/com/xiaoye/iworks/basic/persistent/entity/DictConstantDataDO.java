package com.xiaoye.iworks.basic.persistent.entity;
import com.xiaoye.iworks.persistent.annotation.Column;
import com.xiaoye.iworks.persistent.annotation.Key;
import com.xiaoye.iworks.persistent.annotation.Table;
import com.xiaoye.iworks.persistent.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 实体类【字典分类数据】
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_dict_constant_data", database = "basic")
public class DictConstantDataDO extends Entity {
	private static final long serialVersionUID = 1L;
	@Key(name = "pkid", comments = "自增主键[auto_increment]")
    private Long pkid;
	@Column(name = "dict_code", comments = "字典编码")
    private String dictCode;
	@Column(name = "dict_key", comments = "字典标签")
    private String dictKey;
	@Column(name = "dict_value", comments = "字典键值")
    private String dictValue;
	@Column(name = "dict_order", comments = "字典排序")
    private Integer dictOrder;
	@Column(name = "dict_desc", comments = "字典描述")
    private String dictDesc;
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