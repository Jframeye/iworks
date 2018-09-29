/**
 * 
**/
package com.xiaoye.iworks.core.basic.mq.mq;

import org.springframework.context.annotation.Configuration;

/**
 * @author yehl
 * @date 2018年7月3日 下午2:16:43
 */
@Configuration
public class MQFactoryBean {

	public MQProducer createActiveMQ() {
		return null;
	}
	
	public MQProducer createRabbitMQ() {
		return null;
	}
	
	public MQProducer createRocketMQ() {
		return null;
	}
}
