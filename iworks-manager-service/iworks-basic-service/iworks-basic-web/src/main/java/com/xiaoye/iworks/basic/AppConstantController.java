package com.xiaoye.iworks.basic;

import com.xiaoye.iworks.basic.api.dto.AppConstantDto;
import com.xiaoye.iworks.basic.request.AppConstantFindQueryRequest;
import com.xiaoye.iworks.basic.request.AppConstantListQueryRequest;
import com.xiaoye.iworks.basic.request.AppConstantUpdateRequest;
import com.xiaoye.iworks.common.api.BasicController;
import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.AppConstantService;
import com.xiaoye.iworks.basic.api.input.AppConstantQueryInput;
import com.xiaoye.iworks.common.logger.annotation.RecordLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @RecordLogger
    @RequestMapping(value = "list")
    public Response list(@Valid AppConstantListQueryRequest request) {
        AppConstantQueryInput queryInput = new AppConstantQueryInput();
        return appConstantService.listAppConstant(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "find")
    public Response find(@Valid AppConstantFindQueryRequest request) {
        AppConstantQueryInput queryInput = new AppConstantQueryInput();
        return appConstantService.findAppConstant(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "insert")
    public Response insert(@Valid AppConstantUpdateRequest request) {
        AppConstantDto dto = new AppConstantDto();
        return appConstantService.insertAppConstant(dto);
    }

    @RecordLogger
    @RequestMapping(value = "update")
    public Response update(@Valid AppConstantUpdateRequest request) {
        AppConstantDto dto = new AppConstantDto();
        return appConstantService.updateAppConstant(dto);
    }

    @RecordLogger
    @RequestMapping(value = "delete")
    public Response delete(@Valid AppConstantListQueryRequest request) {
        AppConstantQueryInput queryInput = new AppConstantQueryInput();
        return appConstantService.deleteAppConstant(queryInput);
    }
}
