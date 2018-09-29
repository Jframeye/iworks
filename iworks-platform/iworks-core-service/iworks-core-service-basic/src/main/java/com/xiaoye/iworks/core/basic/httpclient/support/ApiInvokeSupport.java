/**
 * 
**/
package com.xiaoye.iworks.core.basic.httpclient.support;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.PropertyNamingStrategyBase;
import com.xiaoye.iworks.core.basic.httpclient.HttpClientExecutor;
import com.xiaoye.iworks.core.basic.httpclient.HttpClientRequest;
import com.xiaoye.iworks.core.basic.httpclient.annotation.API;
import com.xiaoye.iworks.core.basic.httpclient.exception.ApiException;
import com.xiaoye.iworks.core.basic.httpclient.exception.ApiExceptionCode;
import com.xiaoye.iworks.core.basic.support.SpringContextHolder;
import com.xiaoye.iworks.utils.PropertiesUtils;
import com.xiaoye.iworks.utils.RegularExpression;
import com.xiaoye.iworks.utils.RegularExpressionUtils;
import com.xiaoye.iworks.utils.StringUtils;

/**
 * 接口调用辅助类
 * @author yehl
 * @date 2018年6月23日
 */
public class ApiInvokeSupport {

	static HttpClientExecutor executor = SpringContextHolder.getApplicationContext().getBean(HttpClientExecutor.class);
	
	/**
	 * 接口调用
	 * @param input 输入参数
	 * @param resultClass 返回值类型
	 * @return
	 * @throws ApiException
	 */
	public static <T> T call(ApiInput input, Class<T> resultClass) throws ApiException {
		HttpClientRequest request = check(input);
		String result = executor.invok(request);
		return json2Obj(result, resultClass);
    }
	
	private static <T> T json2Obj(String jsonData, Class<T> resultClass) {
        if(StringUtils.isBlank(jsonData)){
            return null;
        }
        try {
			ObjectMapper objectMapper = initObjectMapper();
			return objectMapper.readValue(jsonData, resultClass);
		} catch (IOException e) {
			throw new ApiException(ApiExceptionCode.API_CONVERT_ERROR, "%JSON数据值转对象异常", e);
		}
    }

	/**
	 * 校验
	 * @param input
	 * @return
	 */
	private static HttpClientRequest check(ApiInput input) {
		API api = input.getClass().getAnnotation(API.class);
		if(api == null) {
			throw new ApiException(ApiExceptionCode.NO_API_ANNOTATION, String.format("%s未使用@API注解标记", input.getClass().getName()));
		}
		// 接口地址校验
		String url = api.url();
		if(!url.startsWith("http") && !url.startsWith("https")) { // 说明使用占位符从配置中获取
			try {
				url = convert(url);
			} catch (Exception e) {
				throw new ApiException(ApiExceptionCode.INVALID_PLACEHOLDER, String.format("%s接口调用地址获取异常", input.getClass().getName()), e);
			}
		}
		// 拼接地址后面的参数
		url = buildUrl(url, input.getParameters(), api.camel2Underline());
		ApiInvokMethod method = api.method();
		if(method == null) {
			throw new ApiException(ApiExceptionCode.NO_API_METHOD, String.format("%s未配置接口调用方式", input.getClass().getName()));
		}
		HttpClientRequest httpClientRequest = new HttpClientRequest(url, method);
		httpClientRequest.setFormData(obj2Json(input, api.camel2Underline()));
		httpClientRequest.setCharset(api.charset());
		httpClientRequest.setTimeout(input.getTimeOut());
		return httpClientRequest;
	}
	
	/**
	 * 输入参数转json字符串
	 * @param input
	 * @param camel2Underline 驼峰是否转下划线
	 * @return
	 */
	private static String obj2Json(ApiInput input, boolean camel2Underline) {
	    try {
			ObjectMapper objectMapper = initObjectMapper();
			return objectMapper.writeValueAsString(input);
		} catch (IOException e) {
			throw new ApiException(ApiExceptionCode.API_CONVERT_ERROR, String.format("%s属性值转JSON字符串异常", input.getClass().getName(), e));
		}
	}

	/**
	 * 获取JSON转换器
	 * @return
	 */
	private static ObjectMapper initObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		/** 下划线转驼峰 **/
		objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategyBase() {
	
			private static final long serialVersionUID = 1L;
	
			@Override
			public String translate(String propertyName) {
				return StringUtils.camel2Underline(propertyName);
			}
		});
		return objectMapper;
	}

	/**
	 * 组装请求地址
	 * @param url
	 * @param parameters
	 * @param camel2Underline 拼接参数是否由驼峰自动转下划线
	 */
	private static String buildUrl(String url, Map<String, String> parameters, boolean camel2Underline) {
		if(parameters.size() != 0) {
			url = String.format("%s?%s", url, buildParameters(parameters, camel2Underline));
		}
		return url;
	}

	/**
	 * 拼接参数
	 * @param parameters
	 * @param camel2Underline 拼接参数是否由驼峰自动转下划线
	 * @return
	 */
	private static Object buildParameters(Map<String, String> parameters, boolean camel2Underline) {
		List<String> list = new ArrayList<>(parameters.keySet());
		// 排序
		Collections.sort(list);
		StringBuffer buffer = new StringBuffer();
		for (String key : list) {
			buffer.append("&");
			buffer.append(String.format("%s=%s", camel2Underline ? StringUtils.camel2Underline(key) : key, parameters.get(key)));
		}
		return buffer.toString().substring(1);
	}

	/**
	 * 占位符转换
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private static String convert(String url) throws Exception {
		// 查找符合 ${} 的字符串
		List<String> list = RegularExpressionUtils.find(RegularExpression.$_, url);
		if(list == null || list.size() == 0) {
			throw new ApiException(ApiExceptionCode.INVALID_PLACEHOLDER, "接口地址使用了不合法的占位符");
		}
		Properties properties = PropertiesUtils.loadProperties("httpclient-restful.properties");
		String finalUrl = url;
		for (String placeholder : list) {
			finalUrl = finalUrl.replace("${" + placeholder + "}", properties.getProperty(placeholder));
		}
		return finalUrl;
	}
}
