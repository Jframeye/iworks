package com.xiaoye.iworks.basic.request;
import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 系统用户登录信息 数据查询入参
 * @auther: auto create by python 
 * @date: 2019-01-24 17:38:50 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginInfoQueryRequest extends Input {
    private static final long serialVersionUID = 1L;
}