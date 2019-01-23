package com.xiaoye.iworks.basic.api.input;

import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 功能描述: 字典分类 查询入参
 * @auther: auto create by python 
 * @date: 2019-01-21 12:55:16 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictConstantQueryInput extends Input {
    private static final long serialVersionUID = 1L;

    /** 自增主键[auto_increment] **/
    private Long pkid;
    private List<Long> pkids;
    /** 字典名称 **/
    private String dictName;
    /** 字典编码 **/
    private String dictCode;
    private List<String> dictCodes;
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

    /** 是否查询分类数据【1：是；2：否】 **/
    private Integer listDictData = 2;
}