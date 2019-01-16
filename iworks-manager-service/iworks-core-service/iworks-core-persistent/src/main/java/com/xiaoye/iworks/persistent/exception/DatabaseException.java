package com.xiaoye.iworks.persistent.exception;

import com.xiaoye.iworks.utils.exception.ServiceException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述: 数据库异常
 * @auther: yehl
 * @date: 2018/12/21 21:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DatabaseException extends ServiceException {
    private static final long serialVersionUID = -2560727580739299324L;

    private static final String errorCode = "3624222019000"; // 异常编码

    public DatabaseException() {
        super(errorCode);
    }

    public DatabaseException(String message) {
        super(errorCode, message);
    }

    public DatabaseException(Throwable throwable) {
        super(errorCode, throwable);
    }

    public DatabaseException(String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }
}
