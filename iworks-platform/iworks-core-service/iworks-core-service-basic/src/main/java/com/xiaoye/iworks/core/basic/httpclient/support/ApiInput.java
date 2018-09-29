package com.xiaoye.iworks.core.basic.httpclient.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * api输入参数基类
 * @author yehl
 * @date 2018年6月23日
 */
public abstract class ApiInput {
	
	/** 设置超时时间 **/
	@JsonIgnore
	private Integer timeOut = null;

	public Integer getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	/** 请求参数 **/
	@JsonIgnore
	private Map<String, String> parameters = new ConcurrentHashMap<>();
	
	/**
	 * 添加URL拼接参数
	 * @param key
	 * @param value
	 * @return
	 */
	public ApiInput addParameter(String key, String value) {
		parameters.put(key, value);
		return this;
	}
	
	public Map<String, String> getParameters() {
		return parameters;
	}
}
