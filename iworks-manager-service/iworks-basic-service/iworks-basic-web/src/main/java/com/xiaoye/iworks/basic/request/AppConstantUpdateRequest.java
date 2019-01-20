package com.xiaoye.iworks.basic.request;

import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 更新入参
 * @auther: yehl
 * @date: 2019/1/18 12:57
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppConstantUpdateRequest extends Input {
    private static final long serialVersionUID = -5998616917085256860L;

    /** 自增主键 **/
    private Long pkid;
    /** 常量编码 **/
    private String constant_key;
    /** 常量值 **/
    private String constant_value;
    /** 常量描述 **/
    private String constant_desc;
    /** 状态：1-可用；2-禁用 **/
    private Integer state;
}
