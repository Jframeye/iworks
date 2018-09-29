/**
 * 
**/
package com.xiaoye.iworks.core.basic.mq.activitemq;

import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author yehl
 * @date 2018年7月3日 下午2:27:55
 */
@Configuration
@PropertySource(value = { "classpath:application-activitemq.properties" })
public class ActiviteMQConfiguration {

	@Autowired
	private Environment environment;
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(environment.getRequiredProperty("activitemq.broker_url").trim());
		factory.setUserName(environment.getProperty("activitemq.username").trim());
		factory.setPassword(environment.getProperty("activitemq.password").trim());
		// TODO 添加属性
		return factory;
	}
	
	@Bean(destroyMethod = "destroy")
	public SingleConnectionFactory pooledConnectionFactory() {
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory((ActiveMQConnectionFactory) connectionFactory());
		// TODO 添加属性
		SingleConnectionFactory connectionFactory = new SingleConnectionFactory(pooledConnectionFactory);
		return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate(pooledConnectionFactory());
		template.setPubSubDomain(environment.getRequiredProperty("activitemq.pub_sub_domain", Boolean.class));
		template.setExplicitQosEnabled(environment.getRequiredProperty("activitemq.explicit_qos_enabled", Boolean.class));
		template.setDeliveryMode(environment.getRequiredProperty("activitemq.delivery_mode", Integer.class));
		template.setDeliveryPersistent(environment.getRequiredProperty("activitemq.delivery_persistent", Boolean.class));
		// TODO 添加属性
		return template;
	}
	
	@Bean
	public JmsMessagingTemplate jmsMessagingTemplate() throws JMSException {
		JmsMessagingTemplate template = new JmsMessagingTemplate(jmsTemplate());
		return template;
	}
	
	@Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(PooledConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
	
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(PooledConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }
}
