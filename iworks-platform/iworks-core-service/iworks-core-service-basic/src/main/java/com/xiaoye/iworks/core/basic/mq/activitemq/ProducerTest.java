/**
 * 
**/
package com.xiaoye.iworks.core.basic.mq.activitemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yehl
 * @date 2018年7月3日 下午12:48:50
 */
@Service
public class ProducerTest {

	@Autowired
	private JmsMessagingTemplate messagingTemplate;
	
	public void sendToQueue() {
		for (int i =0; i < 10; i++) {
			messagingTemplate.convertAndSend(new ActiveMQQueue("TEST.QUEUE1"), "你好，老叶同学, 待消费的队列消息 - " + i);
		}
	}
}
