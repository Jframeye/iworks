/**
 * 
**/
package com.xiaoye.iworks.core.basic.httpclient.exception;

/**
 * 接口调用异常信息
 * @author yehl
 * @date 2018年4月24日 下午4:25:49
 */
public class ApiException extends RuntimeException {

	/**  **/
	private static final long serialVersionUID = 1L;

	public ApiException(String errorCode, String errorInfo) {
//        super(errorCode, errorInfo);
    }

	public ApiException(String errorCode, String errorInfo, Throwable throwable) {
//        super(errorCode, errorInfo, throwable);
    }

	public ApiException(String errorCode, Throwable throwable) {
        this(errorCode, "failure", throwable);
    }

	public ApiException(int errorCode, String errorInfo) {
        this(String.valueOf(errorCode), errorInfo);
    }

	public ApiException(int errorCode, String errorInfo, Throwable throwable) {
        this(String.valueOf(errorCode), errorInfo, throwable);
    }

	public ApiException(int errorCode, Throwable throwable) {
        this(String.valueOf(errorCode), throwable);
    }
}
