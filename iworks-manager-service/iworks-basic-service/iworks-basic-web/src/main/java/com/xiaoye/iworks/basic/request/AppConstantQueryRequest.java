package com.xiaoye.iworks.basic.request;

import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 功能描述: 列表查询入参
 * @auther: yehl
 * @date: 2019/1/18 12:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppConstantQueryRequest extends Input {
    private static final long serialVersionUID = 8549019713708906467L;

    /** 自增主键 **/
    private Long pkid;
    private List<Long> pkids;
    /** 常量编码 **/
    private String constant_key;
    /** 常量值 **/
    private String constant_value;
    /** 常量描述 **/
    private String constant_desc;
    /** 状态：1-可用；2-禁用 **/
    private Integer state;
}
