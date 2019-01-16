package com.xiaoye.iworks.api.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 请求出参封装类
 * @auther: yehl
 * @date: 2019/1/2 19:40
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataResponse<T> extends Response {
    private static final long serialVersionUID = 6062203808212189226L;

    private T data;
}
