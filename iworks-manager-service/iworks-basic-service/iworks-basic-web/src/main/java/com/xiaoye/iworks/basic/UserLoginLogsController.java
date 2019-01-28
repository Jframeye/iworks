package com.xiaoye.iworks.basic;

import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.UserLoginLogsService;
import com.xiaoye.iworks.basic.api.dto.UserLoginLogsDto;
import com.xiaoye.iworks.basic.api.input.UserLoginLogsQueryInput;
import com.xiaoye.iworks.basic.request.UserLoginLogsQueryRequest;
import com.xiaoye.iworks.basic.request.UserLoginLogsUpdateRequest;
import com.xiaoye.iworks.common.api.BasicController;
import com.xiaoye.iworks.common.logger.annotation.RecordLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 【系统用户登录日志】 控制器类
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@RestController
@RequestMapping(value = "user_login_logs", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserLoginLogsController extends BasicController {

    @Autowired
    private UserLoginLogsService userLoginLogsService;

    @RecordLogger
    @RequestMapping(value = "list")
    public Response list(UserLoginLogsQueryRequest request) {
        UserLoginLogsQueryInput queryInput = new UserLoginLogsQueryInput();
        // TODO 参数校验以及参数填充

        return userLoginLogsService.listUserLoginLogss(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "find")
    public Response find(UserLoginLogsQueryRequest request) {
        UserLoginLogsQueryInput queryInput = new UserLoginLogsQueryInput();
        // TODO 参数校验以及参数填充

        return userLoginLogsService.findUserLoginLogs(queryInput);
    }

    @RecordLogger
    @RequestMapping(value = "update")
    public Response update(UserLoginLogsUpdateRequest request) {
        UserLoginLogsDto dto = new UserLoginLogsDto();
        // TODO 参数填充(判断新增or修改)

        if(dto.getPkid() == null) {
            return userLoginLogsService.insertUserLoginLogs(dto);
        } else {
            return userLoginLogsService.updateUserLoginLogs(dto);
        }
    }

    @RecordLogger
    @RequestMapping(value = "delete")
    public Response delete(UserLoginLogsQueryRequest request) {
        UserLoginLogsQueryInput queryInput = new UserLoginLogsQueryInput();
        // TODO 参数校验以及参数填充

        return userLoginLogsService.deleteUserLoginLogs(queryInput);
    }

}