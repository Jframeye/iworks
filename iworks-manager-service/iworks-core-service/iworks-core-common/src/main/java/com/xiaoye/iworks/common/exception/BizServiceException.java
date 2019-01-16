package com.xiaoye.iworks.common.exception;

import com.xiaoye.iworks.utils.exception.ServiceException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 业务异常类
 * @auther: yehl
 * @date: 2019/1/2 17:44
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizServiceException extends ServiceException {
    private static final long serialVersionUID = -8265789278440446967L;

    public BizServiceException() {
        super();
    }

    public BizServiceException(String code) {
        super(code);
    }

    public BizServiceException(String code, Throwable throwable) {
        super(code, throwable);
    }

    public BizServiceException(String code, String message) {
        super(code, message);
    }

    public BizServiceException(String code, String message, Throwable throwable) {
        super(code, message, throwable);
    }
}
