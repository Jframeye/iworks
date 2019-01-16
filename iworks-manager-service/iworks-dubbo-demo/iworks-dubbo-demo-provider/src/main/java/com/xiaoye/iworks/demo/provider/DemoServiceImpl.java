package com.xiaoye.iworks.demo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiaoye.iworks.demo.api.DemoService;

@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {
    @Override
    public String helloworld() {
        return "do something in provider………………";
    }
}
