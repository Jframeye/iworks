/**
 * 
**/
package com.xiaoye.iworks.core.basic.httpclient.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * HttpClient属性配置
 * @author yehl
 * @date 2018年6月23日
 */
@Configuration
@PropertySource(value = {"classpath:application-httpclient.properties"}, encoding = "UTF-8")
@ConfigurationProperties(prefix = "httpclient")
public class HttpClientProperties {

	private int maxRetryCount;
	
	private int timeToLive;
	
	private int maxConnectionTotal;
	
	private int maxPerRoute;
	
	private String proxyHost;
	
	private int proxyPort;
	
	private int connectRequestTimeout;
	
	private int connectTimeout;
	
	private int socketTimeout;

	public int getMaxRetryCount() {
		return maxRetryCount;
	}

	public void setMaxRetryCount(int maxRetryCount) {
		this.maxRetryCount = maxRetryCount;
	}

	public int getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public int getMaxConnectionTotal() {
		return maxConnectionTotal;
	}

	public void setMaxConnectionTotal(int maxConnectionTotal) {
		this.maxConnectionTotal = maxConnectionTotal;
	}

	public int getMaxPerRoute() {
		return maxPerRoute;
	}

	public void setMaxPerRoute(int maxPerRoute) {
		this.maxPerRoute = maxPerRoute;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public int getConnectRequestTimeout() {
		return connectRequestTimeout;
	}

	public void setConnectRequestTimeout(int connectRequestTimeout) {
		this.connectRequestTimeout = connectRequestTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
}
