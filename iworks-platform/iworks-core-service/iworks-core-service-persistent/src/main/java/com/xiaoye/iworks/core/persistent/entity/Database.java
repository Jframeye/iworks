package com.xiaoye.iworks.core.persistent.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 数据持久层基类
 * @author yehl
 * @date 2018/9/27 15:51
 */
@Data
@MappedSuperclass
public class Database implements Serializable {

    private static final long serialVersionUID = -5404931839477329810L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "bigint(20) comment '主键'")
    private Long pkid;

    @Column(nullable = false, columnDefinition = "tinyint(1) comment '状态'")
    private Integer state;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1 comment '逻辑状态(1:正常;2:失效)'")
    private Integer lstate;

    @Column(nullable = false, columnDefinition = "varchar(60) comment '创建人'")
    private String createBy;

    @Column(nullable = false, columnDefinition = "timestamp comment '创建时间'")
    private String createTime;

    @Column(nullable = false, columnDefinition = "varchar(60) comment '修改人'")
    private String updateBy;

    @Column(nullable = false, columnDefinition = "timestamp comment '修改时间'")
    private String updateTime;

    @Column(nullable = true, columnDefinition = "int(11) default 1 comment '更新版本'")
    private int version;

    /** 分库下标 **/
    @Transient
    private Integer dbIndex;

    /** 分表下标 **/
    @Transient
    private Integer tbIndex;
}
