/**
 * 
**/
package com.xiaoye.iworks.core.basic.mq.activitemq.withoutspring;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;

/**
 * @author yehl
 * @date 2018年7月4日 下午3:07:02
 */
public class WithoutSpringProducer {

	public static void main(String[] args) throws JMSException {
		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		// 创建连接池
		PooledConnectionFactory factory = new PooledConnectionFactory(activeMQConnectionFactory);
		
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
		
		// 目的地
		Destination destination = session.createQueue("TEST.QUEUE");
		
		// 生产者
		MessageProducer producer = session.createProducer(destination);
		TextMessage message = session.createTextMessage("这是我发生的消息…………");
		producer.send(message);
		
		// 关闭
	}
}
