package com.xiaoye.iworks.basic.request;
import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 用户基本信息 数据查询入参
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserBasicInfoQueryRequest extends Input {
    private static final long serialVersionUID = 1L;
}