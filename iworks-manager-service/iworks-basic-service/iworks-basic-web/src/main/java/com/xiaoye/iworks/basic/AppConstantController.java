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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "list")
    public Response list(@RequestBody AppConstantQueryRequest request) {
        AppConstantQueryInput input = new AppConstantQueryInput();
        input.setConstantKey(request.getConstant_key());
        input.setConstantValue(request.getConstant_value());
        input.setConstantDesc(request.getConstant_desc());
        input.setState(request.getState());
        return appConstantService.listAppConstant(input);
    }

    @RecordLogger
    @RequestMapping(value = "find")
    public Response find(@RequestBody AppConstantQueryRequest request) {
        AppConstantQueryInput input = new AppConstantQueryInput();
        input.setPkid(request.getPkid());
        input.setConstantKey(request.getConstant_key());
        return appConstantService.findAppConstant(input);
    }

//    @CheckSession(permission = "")
    @RecordLogger
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Response update(@RequestBody AppConstantUpdateRequest request) {
        // TODO 参数校验
        {
            // TODO 测试代码，需删除
            request.setCurrentNickName("叶宏梁");
        }

        AppConstantDto dto = new AppConstantDto();
        dto.setPkid(request.getPkid());
        dto.setConstantKey(request.getConstant_key());
        dto.setConstantValue(request.getConstant_value());
        dto.setConstantDesc(request.getConstant_desc());
        dto.setState(request.getState());
        dto.setCreateBy(request.getCurrentNickName());
        dto.setModifyBy(request.getCurrentNickName());
        if(dto.getPkid() == null) {
            return appConstantService.insertAppConstant(dto);
        }
        return appConstantService.updateAppConstant(dto);
    }

    @RecordLogger
    @RequestMapping(value = "delete")
    public Response delete(@RequestBody AppConstantQueryRequest request) {
        AppConstantQueryInput input = new AppConstantQueryInput();
        input.setPkid(request.getPkid());
        input.setPkids(request.getPkids());
        return appConstantService.deleteAppConstant(input);
    }
}
