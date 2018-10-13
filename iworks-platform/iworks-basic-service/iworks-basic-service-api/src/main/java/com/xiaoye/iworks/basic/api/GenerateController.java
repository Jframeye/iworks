package com.xiaoye.iworks.basic.api;

import com.xiaoye.iworks.core.api.BasicController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码生成器接口
 * @author yehl
 * @date 2018/9/30 17:43
 */
@RestController
@RequestMapping(value = "api/generate")
public class GenerateController extends BasicController {

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public void listByCondition() {

    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    public void findByCondition() {

    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public void insert() {

    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete() {

    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update() {

    }
}
