package com.xiaoye.iworks.basic.api.input;

import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 功能描述: 字典分类数据 查询入参
 * @auther: auto create by python 
 * @date: 2019-01-22 11:11:24 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictConstantDataQueryInput extends Input {
    private static final long serialVersionUID = 1L;

    /** 自增主键[auto_increment] **/
    private Long pkid;
    private List<Long> pkids;
    /** 字典编码 **/
    private String dictCode;
    private List<String> dictCodes;
    /** 字典标签 **/
    private String dictKey;
    private List<String> dictKeys;
    /** 字典键值 **/
    private String dictValue;
    /** 字典排序 **/
    private Integer dictOrder;
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
}