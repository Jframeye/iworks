package com.xiaoye.iworks.basic.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述: 系统常量传输类
 * @auther: yehl
 * @date: 2019/1/4 12:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppConstantDto implements Serializable {
    private static final long serialVersionUID = 745681150561987728L;

    /** 自增主键 **/
    private Long pkid;
    /** 常量编码 **/
    private String constantKey;
    /** 常量值 **/
    private String constantValue;
    /** 常量描述 **/
    private String constantDesc;
    /** 状态：1-可用；2-禁用 **/
    private Integer state;
    /** 创建时间 **/
    private Date createTime;
    /** 创建人 **/
    private String createBy;
    /** 最后修改时间 **/
    private Date modifyTime;
    /** 最后修改人 **/
    private String modifyBy;
}
