/**
 * 
**/
package com.xiaoye.iworks.core.basic.mq.activitemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author yehl
 * @date 2018年7月3日 下午2:10:52
 */
@Service
public class ConsumerTest1 {

	@JmsListener(destination = "TEST.QUEUE1", containerFactory = "jmsListenerContainerQueue", concurrency = "2-6")
	public void receiveQueue1(String message) {
		System.out.println("ConsumerTest1 ---- " + message);
	}
}
