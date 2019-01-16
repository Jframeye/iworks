package com.xiaoye.iworks.demo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiaoye.iworks.demo.api.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerService {

    @Reference(version = "1.0.0")
    private DemoService service;

    @RequestMapping("test")
    public String execute() {
        return service.helloworld();
    }
}
