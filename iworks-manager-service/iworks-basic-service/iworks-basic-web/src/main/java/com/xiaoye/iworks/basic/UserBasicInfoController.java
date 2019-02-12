package com.xiaoye.iworks.basic;

import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.UserBasicInfoService;
import com.xiaoye.iworks.basic.api.dto.UserBasicInfoDto;
import com.xiaoye.iworks.basic.api.input.UserBasicInfoQueryInput;
import com.xiaoye.iworks.basic.core.support.UserInfoAuxiliaryService;
import com.xiaoye.iworks.basic.request.UserBasicInfoQueryRequest;
import com.xiaoye.iworks.basic.request.UserBasicInfoUpdateRequest;
import com.xiaoye.iworks.common.api.BasicController;
import com.xiaoye.iworks.common.logger.annotation.RecordLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 【用户基本信息】 控制器类
 * @auther: auto create by python 
 * @date: 2019-01-25 16:57:59 
 */
@RestController
@RequestMapping(value = "user_basic_info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserBasicInfoController extends BasicController {

    @Autowired
    private UserBasicInfoService userBasicInfoService;
    @Autowired
    private UserInfoAuxiliaryService userInfoAuxiliaryService;

    @RecordLogger
    @RequestMapping(value = "list")
    public Response list(UserBasicInfoQueryRequest request) {
        UserBasicInfoQueryInput queryInput = new UserBasicInfoQueryInput();
        // TODO 参数校验以及参数填充

        return userBasicInfoService.listUserBasicInfos(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "find")
    public Response find(@RequestBody UserBasicInfoQueryRequest request) {
        UserBasicInfoQueryInput queryInput = new UserBasicInfoQueryInput();
        // TODO 参数校验以及参数填充

        return userBasicInfoService.findUserBasicInfo(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "update")
    public Response update(UserBasicInfoUpdateRequest request) {
        UserBasicInfoDto dto = new UserBasicInfoDto();
        // TODO 参数填充(判断新增or修改)

        if(dto.getPkid() == null) {
            return userBasicInfoService.insertUserBasicInfo(dto);
        } else {
            return userBasicInfoService.updateUserBasicInfo(dto);
        }
    }

    @RecordLogger
    @RequestMapping(value = "delete")
    public Response delete(UserBasicInfoQueryRequest request) {
        UserBasicInfoQueryInput queryInput = new UserBasicInfoQueryInput();
        // TODO 参数校验以及参数填充

        return userBasicInfoService.deleteUserBasicInfo(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "findUserInfoAndPermissions")
    public Response findUserInfoAndPermissions(@RequestBody UserBasicInfoQueryRequest request) {
        UserBasicInfoQueryInput queryInput = new UserBasicInfoQueryInput();
        // TODO 参数校验以及参数填充

        return userInfoAuxiliaryService.findUserInfoAndPermissions(queryInput);
    }
}