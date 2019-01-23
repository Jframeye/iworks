package com.xiaoye.iworks.basic.api.dto;

import com.xiaoye.iworks.api.input.Input;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 字典分类 查询入参
 * @auther: auto create by python 
 * @date: 2019-01-22 10:19:47 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictConstantDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 自增主键[auto_increment] **/
    private Long pkid;
    /** 字典名称 **/
    private String dictName;
    /** 字典编码 **/
    private String dictCode;
    /** 字典描述 **/
    private String dictDesc;
    /** 数据签名 **/
    private String sign;
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
    /** 字典分类数据 **/
    private List<DictConstantDataDto> dictDataList;
}