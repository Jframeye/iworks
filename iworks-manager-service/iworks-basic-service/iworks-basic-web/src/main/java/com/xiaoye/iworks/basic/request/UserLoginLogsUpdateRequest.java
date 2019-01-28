package com.xiaoye.iworks.basic.request;
import com.xiaoye.iworks.api.input.Input;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 系统用户登录日志 数据更新入参
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginLogsUpdateRequest extends Input {
    private static final long serialVersionUID = 1L;
}