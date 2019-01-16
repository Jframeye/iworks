package com.xiaoye.iworks.basic.api.input;

import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 功能描述: 系统常量查询入参
 * @auther: yehl
 * @date: 2019/1/4 12:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppConstantQueryInput extends Input {
    private static final long serialVersionUID = 1294512239971957891L;

    /** 自增主键 **/
    private Long pkid;
    private List<Long> pkids;
    /** 常量编码 **/
    private String constantKey;
    private List<String> constantKeys;
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
