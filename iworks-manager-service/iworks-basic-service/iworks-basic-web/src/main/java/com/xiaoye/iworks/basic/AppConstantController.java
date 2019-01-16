package com.xiaoye.iworks.basic;

import com.xiaoye.iworks.api.BasicController;
import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.AppConstantService;
import com.xiaoye.iworks.basic.api.input.AppConstantQueryInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 系统常量
 * @auther: yehl
 * @date: 2019/1/2 21:01
 */
@RestController
@RequestMapping(value = "app_constant", produces = "application/json")
public class AppConstantController extends BasicController {

    @Autowired
    private AppConstantService appConstantService;

    @RequestMapping(value = "list")
    public Response list() {
        AppConstantQueryInput queryInput = new AppConstantQueryInput();
        return appConstantService.listAppConstant(queryInput);
    }
}
