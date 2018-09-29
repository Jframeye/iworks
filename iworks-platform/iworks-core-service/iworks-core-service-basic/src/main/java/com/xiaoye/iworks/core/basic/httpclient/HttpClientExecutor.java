/**
 * 
**/
package com.xiaoye.iworks.core.basic.httpclient;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoye.microservices.basic.httpclient.support.ApiInvokMethod;

/**
 * HttpClient 执行器
 * @author yehl
 * @date 2018年6月23日
 */
@Service
public class HttpClientExecutor {

	@Autowired
	private CloseableHttpClient httpClient;
	
	public String invok(HttpClientRequest request) {
		if(request.getInvokMethod() == ApiInvokMethod.GET) {
			return this.get(request.getUrl(), request.getCharset());
		} else if(request.getInvokMethod() == ApiInvokMethod.POST) {
			return this.post(request.getUrl(), request.getCharset(), request.getFormData());
		}
		return null;
	}
	
	/**
	 * Get 请求
	 * @param url
	 * @param charset
	 * @return
	 */
	public String get(String url, String charset) {
		HttpEntity httpEntity = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			httpEntity = response.getEntity();
			String type = ContentType.getOrDefault(httpEntity).getMimeType();
			if (ContentType.APPLICATION_JSON.getMimeType().equalsIgnoreCase(type)) {
				return EntityUtils.toString(httpEntity, charset);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			EntityUtils.consumeQuietly(httpEntity);// 释放连接
		}
		return null;
	}
	
	public String post(String url, String charset, String formData) {
		HttpEntity httpEntity = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			
	        // 构造消息头
			httpPost.setHeader("Content-type", "application/json; charset=utf-8");
			httpPost.setHeader("Connection", "Close");
			httpPost.setHeader("SessionId", "");
			httpPost.setHeader("appid", "");
	                    
	        // 构建消息实体
	        StringEntity entity = new StringEntity(formData, Charset.forName(charset));
	        entity.setContentEncoding(charset);
	        
	        // 发送Json格式的数据请求
	        entity.setContentType("application/json");
	        httpPost.setEntity(entity);
			
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
	        if(statusCode != HttpStatus.SC_OK){
//	        	throw new ApiException("", "接口请求失败"); TODO
	        }
			httpEntity = response.getEntity();
			String type = ContentType.getOrDefault(httpEntity).getMimeType();
			// ContentType为如下类型时，将响应内容作为字符串返回
			if (ContentType.APPLICATION_JSON.getMimeType().equalsIgnoreCase(type)
                    || ContentType.APPLICATION_XML.getMimeType().equalsIgnoreCase(type)
                    || ContentType.TEXT_HTML.getMimeType().equalsIgnoreCase(type)
                    || ContentType.TEXT_PLAIN.getMimeType().equalsIgnoreCase(type)
                    || ContentType.TEXT_XML.getMimeType().equalsIgnoreCase(type)) {
				return EntityUtils.toString(httpEntity, charset);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			EntityUtils.consumeQuietly(httpEntity);// 释放连接
		}
		return null;
	}
}
