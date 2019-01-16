package com.xiaoye.iworks.utils.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 异常基类
 * @auther: yehl
 * @date: 2019/1/2 17:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -7061378547195774654L;

    String code;
    String message;

    public ServiceException() {
        super();
    }

    public ServiceException(String code) {
        super();
        this.code = code;
    }

    public ServiceException(String code, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.message = throwable.getMessage();
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ServiceException(String code, String message, Throwable throwable) {
        super(throwable);
        this.code = code;
        this.message = message;
    }
}
