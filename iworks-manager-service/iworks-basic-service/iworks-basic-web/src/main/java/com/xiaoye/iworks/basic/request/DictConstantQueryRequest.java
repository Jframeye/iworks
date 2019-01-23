package com.xiaoye.iworks.basic.request;

import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 功能描述: 字典分类 数据查询入参
 * @auther: auto create by python 
 * @date: 2019-01-21 21:36:16 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DictConstantQueryRequest extends Input {
    private static final long serialVersionUID = 1L;

    /** 自增主键 **/
    private Long pkid;
    private List<Long> pkids;
    /** 字典名称 **/
    private String dict_name;
    /** 字典编码 **/
    private String dict_code;
    private List<String> dict_codes;
    /** 字典描述 **/
    private String dict_desc;
    /** 状态[1-可用；2-禁用] **/
    private Integer state;
    /** 创建人 **/
    private String create_by;
    /** 创建时间 **/
    private Date create_time;
    /** 最后修改人 **/
    private String modify_by;
    /** 最后修改时间 **/
    private Date modify_time;

    /** 是否查询分类数据【1：是；2：否】 **/
    private Integer list_dict_data = 2;
}