package com.xiaoye.iworks.basic;

import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.AppConstantService;
import com.xiaoye.iworks.basic.api.dto.AppConstantDto;
import com.xiaoye.iworks.basic.api.input.AppConstantQueryInput;
import com.xiaoye.iworks.basic.request.AppConstantQueryRequest;
import com.xiaoye.iworks.basic.request.AppConstantUpdateRequest;
import com.xiaoye.iworks.common.api.BasicController;
import com.xiaoye.iworks.common.logger.annotation.RecordLogger;
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

    @RecordLogger
    @RequestMapping(value = "list")
    public Response list(AppConstantQueryRequest request) {
        AppConstantQueryInput queryInput = new AppConstantQueryInput();
        queryInput.setConstantKey(request.getConstant_key());
        queryInput.setConstantValue(request.getConstant_value());
        queryInput.setConstantDesc(request.getConstant_desc());
        queryInput.setState(request.getState());
        return appConstantService.listAppConstant(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "find")
    public Response find(AppConstantQueryRequest request) {
        AppConstantQueryInput queryInput = new AppConstantQueryInput();
        queryInput.setPkid(request.getPkid());
        queryInput.setConstantKey(request.getConstant_key());
        return appConstantService.findAppConstant(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "update")
    public Response update(AppConstantUpdateRequest request) {
        AppConstantDto dto = new AppConstantDto();
        dto.setPkid(request.getPkid());
        dto.setConstantKey(request.getConstant_key());
        dto.setConstantValue(request.getConstant_value());
        dto.setConstantDesc(request.getConstant_desc());
        dto.setState(request.getState());
        if(dto.getPkid() == null) {
            return appConstantService.insertAppConstant(dto);
        }
        return appConstantService.updateAppConstant(dto);
    }

    @RecordLogger
    @RequestMapping(value = "delete")
    public Response delete(AppConstantQueryRequest request) {
        AppConstantQueryInput queryInput = new AppConstantQueryInput();
        queryInput.setPkid(request.getPkid());
        queryInput.setPkids(request.getPkids());
        return appConstantService.deleteAppConstant(queryInput);
    }
}
