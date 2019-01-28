package com.xiaoye.iworks.basic.api.dto;

import com.xiaoye.iworks.api.input.Input;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

/**
 * 功能描述: 字典分类数据 查询入参
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictConstantDataDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 自增主键[auto_increment] **/
    private Long pkid;
    /** 字典编码 **/
    private String dictCode;
    /** 字典标签 **/
    private String dictKey;
    /** 字典键值 **/
    private String dictValue;
    /** 字典排序 **/
    private Integer dictOrder;
    /** 字典描述 **/
    private String dictDesc;
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