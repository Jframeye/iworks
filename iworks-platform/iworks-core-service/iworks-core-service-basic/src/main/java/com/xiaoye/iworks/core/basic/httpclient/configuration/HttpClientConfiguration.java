/**
 * 
**/
package com.xiaoye.iworks.core.basic.httpclient.configuration;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLException;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HttpClient 配置类
 * @author yehl
 * @date 2018年6月23日
 */
@Configuration
public class HttpClientConfiguration {

	@Autowired
	private HttpClientProperties properties;
	
	/**
	 * 重试机制
	 * @return
	 */
	@Bean
	public HttpRequestRetryHandler httpRequestRetryHandler() {
		final int maxRetryCount = properties.getMaxRetryCount();
		return new HttpRequestRetryHandler() {
			
			@Override
			public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
				// 如果重试次数超过了retryTime,则不再重试请求
				if (executionCount > maxRetryCount) {
					return false;
				}
				// 服务端断掉客户端的连接异常
				if (exception instanceof NoHttpResponseException) {
					return true;
				}
				// time out 超时重试
				if (exception instanceof InterruptedIOException) {
					return false;
				}
				// Unknown host
				if (exception instanceof UnknownHostException) {
					return false;
				}
				// Connection refused
				if (exception instanceof ConnectTimeoutException) {
					return false;
				}
				// SSL handshake exception
				if (exception instanceof SSLException) {
					return false;
				}
				HttpClientContext clientContext = HttpClientContext.adapt(context);  
                HttpRequest request = clientContext.getRequest();  
                if (!(request instanceof HttpEntityEnclosingRequest)) {  
                    return true;  
                }  
                return false;
			}
		};
	}
	
	/**
	 * 连接池管理
	 * @return
	 */
	@Bean
	public PoolingHttpClientConnectionManager httpClientConnectionPoolManager() {
		PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(properties.getTimeToLive(), TimeUnit.SECONDS);
		manager.setMaxTotal(properties.getMaxConnectionTotal());
		manager.setDefaultMaxPerRoute(properties.getMaxPerRoute());
		return manager;
	}
	
	/**
	 * 连接保持策略 
	 * @return
	 */
	@Bean
	public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return new ConnectionKeepAliveStrategy() {
        	
        	@Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) { 
                // Honor 'keep-alive' header
                HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                return 30 * 1000;
            }
        };
    }
	
	/**
	 * 代理配置
	 * @return
	 */
	@Bean  
    public DefaultProxyRoutePlanner defaultProxyRoutePlanner(){
        HttpHost proxy = new HttpHost(properties.getProxyHost(), properties.getProxyPort());
        return new DefaultProxyRoutePlanner(proxy);
    }
	
	@Bean  
    public RequestConfig config(){  
        return RequestConfig.custom()  
                .setConnectionRequestTimeout(properties.getConnectRequestTimeout())  
                .setConnectTimeout(properties.getConnectTimeout())  
                .setSocketTimeout(properties.getSocketTimeout())  
                .build();  
    }
}
