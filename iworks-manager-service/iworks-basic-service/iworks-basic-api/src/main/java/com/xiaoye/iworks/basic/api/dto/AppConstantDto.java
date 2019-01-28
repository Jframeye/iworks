package com.xiaoye.iworks.basic.api.dto;

import com.xiaoye.iworks.api.input.Input;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 系统常量数据 查询入参
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppConstantDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 自增主键[auto_increment] **/
    private Long pkid;
    /** 常量编码 **/
    private String constantKey;
    /** 常量值 **/
    private String constantValue;
    /** 常量说明 **/
    private String constantDesc;
    /** 标签类型[success,info,warning,danger] **/
    private String tagType;
    /** 状态[1-可用；2-禁用] **/
    private Integer state;
    /** 创建人 **/
    private String createBy;
    /** 创建时间 **/
    private Date createTime;
    /** 最后修改人 **/
    private String modifyBy;
    /** 最后修改时间 **/
    private Date modifyTime;
}