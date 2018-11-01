package com.xiaoye.iworks.core.api.result;

import java.io.Serializable;

/**
 * 接口请求出参
 * @author yehl
 * @date 2018年6月23日
 */
public class Result implements Serializable {

	/** **/
	private static final long serialVersionUID = 2953828433156103191L;

	private int status = 200;
	
	private String message = "success";
	
	private Object data;

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Result() {
	}
	
	public Result(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	/**
	 * 成功返回
	 * @param data 返回的数据
	 * @return
	 */
	public static Result success(Object data) {
		return new Result(200, "success", data);
	}

	/**
	 * 成功返回
	 * @return
	 */
	public static Result success() {
		return new Result(200, "success", null);
	}
	
	/**
	 * 失败返回
	 * @param status
	 * @param message
	 * @return
	 */
	public static Result failure(int status, String message) {
		return new Result(status, message, null);
	}
}
