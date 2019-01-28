package com.xiaoye.iworks.basic;

import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.AppConstantService;
import com.xiaoye.iworks.basic.api.dto.AppConstantDto;
import com.xiaoye.iworks.basic.api.input.AppConstantQueryInput;
import com.xiaoye.iworks.basic.request.AppConstantQueryRequest;
import com.xiaoye.iworks.basic.request.AppConstantUpdateRequest;
import com.xiaoye.iworks.common.api.BasicController;
import com.xiaoye.iworks.common.logger.annotation.RecordLogger;
import com.xiaoye.iworks.common.session.annotation.CheckSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 系统常量
 * @auther: yehl
 * @date: 2019/1/2 21:01
 */
@RestController
@RequestMapping(value = "app_constant", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppConstantController extends BasicController {

    @Autowired
    private AppConstantService appConstantService;

    @RecordLogger
    @CheckSession(permission = "")
    @RequestMapping(value = "list")
    public Response list(AppConstantQueryRequest request) {
        AppConstantQueryInput input = new AppConstantQueryInput();
        input.setConstantKey(request.getConstant_key());
        input.setConstantValue(request.getConstant_value());
        input.setConstantDesc(request.getConstant_desc());
        input.setState(request.getState());
        return appConstantService.listAppConstant(input);
    }

    @RecordLogger
    @CheckSession(permission = "")
    @RequestMapping(value = "find")
    public Response find(AppConstantQueryRequest request) {
        AppConstantQueryInput input = new AppConstantQueryInput();
        input.setPkid(request.getPkid());
        input.setConstantKey(request.getConstant_key());
        return appConstantService.findAppConstant(input);
    }

    @RecordLogger
    @CheckSession(permission = "")
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
    @CheckSession(permission = "")
    @RequestMapping(value = "delete")
    public Response delete(AppConstantQueryRequest request) {
        AppConstantQueryInput input = new AppConstantQueryInput();
        input.setPkid(request.getPkid());
        input.setPkids(request.getPkids());
        return appConstantService.deleteAppConstant(input);
    }
}
