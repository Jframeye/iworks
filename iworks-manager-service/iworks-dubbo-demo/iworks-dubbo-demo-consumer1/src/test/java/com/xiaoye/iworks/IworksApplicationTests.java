package com.xiaoye.iworks;

import com.xiaoye.iworks.demo.consumer.ConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IworksApplicationTests {

	@Resource
	private ConsumerService service;

	@Test
	public void contextLoads() {
		System.out.println(service.execute());
	}
}

