package com.xiaoye.iworks.basic;

import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.UserLoginInfoService;
import com.xiaoye.iworks.basic.api.dto.UserLoginInfoDto;
import com.xiaoye.iworks.basic.api.input.UserLoginInfoQueryInput;
import com.xiaoye.iworks.basic.request.UserLoginInfoQueryRequest;
import com.xiaoye.iworks.basic.request.UserLoginInfoUpdateRequest;
import com.xiaoye.iworks.common.api.BasicController;
import com.xiaoye.iworks.common.logger.annotation.RecordLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 【系统用户登录信息】 控制器类
 * @auther: auto create by python 
 * @date: 2019-01-24 17:38:50 
 */
@RestController
@RequestMapping(value = "user_login_info", produces = "application/json")
public class UserLoginInfoController extends BasicController {

    @Autowired
    private UserLoginInfoService userLoginInfoService;

    @RecordLogger
    @RequestMapping(value = "list")
    public Response list(UserLoginInfoQueryRequest request) {
        UserLoginInfoQueryInput queryInput = new UserLoginInfoQueryInput();
        // TODO 参数校验以及参数填充

        return userLoginInfoService.listUserLoginInfos(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "find")
    public Response find(UserLoginInfoQueryRequest request) {
        UserLoginInfoQueryInput queryInput = new UserLoginInfoQueryInput();
        // TODO 参数校验以及参数填充

        return userLoginInfoService.findUserLoginInfo(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "update")
    public Response update(UserLoginInfoUpdateRequest request) {
        UserLoginInfoDto dto = new UserLoginInfoDto();
        // TODO 参数填充(判断新增or修改)

        if(dto.getPkid() == null) {
            return userLoginInfoService.insertUserLoginInfo(dto);
        } else {
            return userLoginInfoService.updateUserLoginInfo(dto);
        }
    }

    @RecordLogger
    @RequestMapping(value = "delete")
    public Response delete(UserLoginInfoQueryRequest request) {
        UserLoginInfoQueryInput queryInput = new UserLoginInfoQueryInput();
        // TODO 参数校验以及参数填充

        return userLoginInfoService.deleteUserLoginInfo(queryInput);
    }

}