package com.xiaoye.iworks.persistent.entity;
import com.xiaoye.iworks.persistent.annotation.Column;
import com.xiaoye.iworks.persistent.annotation.Key;
import com.xiaoye.iworks.persistent.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 实体类【系统常量信息】
 * @auther: auto create by python 
 * @date: 2019-01-10 13:34:22 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "t_table_name", database = "db_name")
public class TableNameDO extends Entity {
	private static final long serialVersionUID = 1L;
	@Key(name = "pkid", comments = "自增主键[auto_increment]")
    private Long pkid;
	@Column(name = "column_1", comments = "字段1")
    private String column1;
	@Column(name = "column_2", comments = "字段2")
    private String column2;
	@Column(name = "column_3", comments = "字段3")
    private String column3;
	@Column(name = "sign", comments = "数据签名")
    private String sign;
	@Column(name = "state", comments = "状态[1-可用；2-禁用]")
    private Integer state;
	@Column(name = "lstate", comments = "逻辑状态[1-正常；2-删除]")
    private Integer lstate;
	@Column(name = "create_time", comments = "创建时间")
    private Date createTime;
	@Column(name = "modify_time", comments = "最后修改时间")
    private Date modifyTime;
}