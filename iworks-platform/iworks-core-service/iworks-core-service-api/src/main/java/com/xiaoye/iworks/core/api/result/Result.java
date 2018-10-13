package com.xiaoye.iworks.core.api.result;

import java.io.Serializable;

/**
 * 接口请求出参
 * @author yehl
 * @date 2018年6月23日
 */
public class Result<T> implements Serializable {

	/** **/
	private static final long serialVersionUID = 2953828433156103191L;

	private int status = 200;
	
	private String message = "success";
	
	private T data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public Result() {
	}
	
	public Result(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	/**
	 * 成功返回
	 * @param data
	 * @return
	 */
	public static <T> Result<T> success(T data) {
		return new Result<T>(200, "success", data);
	}
	
	/**
	 * 失败返回
	 * @param status
	 * @param message
	 * @return
	 */
	public static <T> Result<T> failure(int status, String message) {
		return new Result<T>(status, message, null);
	}
}
