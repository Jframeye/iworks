/**
 * 
**/
package com.xiaoye.iworks.core.basic.httpclient;

import com.xiaoye.iworks.core.basic.httpclient.support.ApiInvokMethod;

/**
 * 接口请求相关信息
 * TODO
 * @author yehl
 * @date 2018年6月23日
 */
public class HttpClientRequest {

	private String url;
	
	private String charset;
	
	private ApiInvokMethod invokMethod;
	
	private String formData;
	
	private Integer timeout;
	
	public HttpClientRequest() {
	}
	
	public HttpClientRequest(String url, ApiInvokMethod invokMethod) {
		this.url = url;
		this.invokMethod = invokMethod;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getFormData() {
		return formData;
	}

	public void setFormData(String formData) {
		this.formData = formData;
	}

	public ApiInvokMethod getInvokMethod() {
		return invokMethod;
	}

	public void setInvokMethod(ApiInvokMethod invokMethod) {
		this.invokMethod = invokMethod;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
}
