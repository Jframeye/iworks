package com.xiaoye.iworks.basic;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.basic.api.UserLoginInfoService;
import com.xiaoye.iworks.basic.api.constant.UserLoginInfoConstant;
import com.xiaoye.iworks.basic.api.dto.UserLoginInfoDto;
import com.xiaoye.iworks.basic.api.dto.UserLoginLogsDto;
import com.xiaoye.iworks.basic.api.input.UserLoginInfoQueryInput;
import com.xiaoye.iworks.basic.auxiliary.UserLoginAuxiliary;
import com.xiaoye.iworks.basic.core.exception.UserLoginInfoErrorCode;
import com.xiaoye.iworks.basic.request.UserLoginInfoQueryRequest;
import com.xiaoye.iworks.basic.request.UserLoginInfoUpdateRequest;
import com.xiaoye.iworks.common.api.BasicController;
import com.xiaoye.iworks.common.constant.SessionConstant;
import com.xiaoye.iworks.common.exception.BizServiceException;
import com.xiaoye.iworks.common.logger.annotation.RecordLogger;
import com.xiaoye.iworks.common.session.token.Token;
import com.xiaoye.iworks.common.session.token.TokenFactory;
import com.xiaoye.iworks.common.support.ResponseCheck;
import com.xiaoye.iworks.service.RedisCacheService;
import com.xiaoye.iworks.utils.DateTimeUtils;
import com.xiaoye.iworks.utils.EncryptUtil;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.utils.WebUtils;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: 【系统用户登录信息】 控制器类
 * @auther: auto create by python 
 * @date: 2019-01-25 17:06:25 
 */
@RestController
@RequestMapping(value = "user_login_info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserLoginInfoController extends BasicController {

    @Autowired
    private UserLoginInfoService userLoginInfoService;
    @Autowired
    private UserLoginAuxiliary userLoginAuxiliary;


    @RecordLogger
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Response register(UserLoginInfoUpdateRequest request) {
        Response response = new Response();
        // TODO 参数校验以及参数填充
        // 判断用户是否已注册
        UserLoginInfoQueryInput input = new UserLoginInfoQueryInput();
        input.setUserName(request.getUser_name());
        DataResponse<UserLoginInfoDto> findResponse = userLoginInfoService.findUserLoginInfo(input);
        ResponseCheck.checkSuccess(findResponse);
        if(findResponse.getData() != null) {
            response.setRetcode(UserLoginInfoErrorCode.DATA_EXIST_ERROR);
            response.setMessage("用户账号已被使用");
            return response;
        }
        if(!request.getPass_word().equals(request.getConfirm_pass())) {
            response.setRetcode(UserLoginInfoErrorCode.PARAM_ERROR);
            response.setMessage("密码不一致");
            return response;
        }
        UserLoginInfoDto dto = new UserLoginInfoDto();
        dto.setNickName(request.getNick_name());
        dto.setUserName(request.getUser_name());
        dto.setPassword(request.getPass_word());
        dto.setEmail(request.getEmail());
        dto.setMobile(request.getMobile());
        DataResponse<Long> insertResponse = userLoginInfoService.insertUserLoginInfo(dto);
        ResponseCheck.checkSuccess(insertResponse);
        return response;
    }

    @RecordLogger
    @RequestMapping(value = "loginByUsername", method = RequestMethod.POST)
    public Response loginByUsername(UserLoginInfoQueryRequest request, HttpServletRequest servletRequest) {
        DataResponse<String> response = new DataResponse();
        // 参数校验
        if(StringUtils.hasBlankString(request.getUser_name(), request.getPass_word())) {
            response.setRetcode(UserLoginInfoErrorCode.USER_LOGIN_ERROR);
            response.setMessage("账户和密码不能为空");
            return response;
        }

        // 根据账号查找用户
        UserLoginInfoQueryInput input = new UserLoginInfoQueryInput();
        input.setUserName(request.getUser_name());
        input.setCheckNull(true);
        DataResponse<UserLoginInfoDto> dataResponse = userLoginInfoService.findUserLoginInfo(input);
        if(!ResponseCheck.checkSilent(dataResponse)) {
            response.setRetcode(UserLoginInfoErrorCode.USER_LOGIN_ERROR);
            response.setMessage("账号不存在");
            return response;
        }
        UserLoginInfoDto infoDto = dataResponse.getData();

        // 判断账号状态
        if(UserLoginInfoConstant.State.FREEZE.equals(infoDto.getState())) {
            response.setRetcode(UserLoginInfoErrorCode.USER_LOGIN_ERROR);
            response.setMessage("账号已被冻结，不能使用");
            return response;
        }
        if(UserLoginInfoConstant.State.DISCARD.equals(infoDto.getState())) {
            response.setRetcode(UserLoginInfoErrorCode.USER_LOGIN_ERROR);
            response.setMessage("账号已被废弃，不能使用");
            return response;
        }
        UserLoginInfoDto loginInfoDto = new UserLoginInfoDto();
        loginInfoDto.setPkid(infoDto.getPkid());
        if(UserLoginInfoConstant.State.LOCKED.equals(infoDto.getState())) {
            if(infoDto.getLockTime() != null && DateTimeUtils.compare(DateTimeUtils.currentDate(), "<", infoDto.getLockTime())) {
                response.setRetcode(UserLoginInfoErrorCode.USER_LOGIN_ERROR);
                response.setMessage(String.format("账号已被锁定，%s后将自动解锁. 如需现在解锁，请联系客服人员", DateTimeUtils.formatDateToString(infoDto.getLockTime())));
                return response;
            } else {
                loginInfoDto.setState(UserLoginInfoConstant.State.NORMAL);
                infoDto.setErrorCount(0);
                loginInfoDto.setErrorCount(0);
                loginInfoDto.setLockTime(null);
                userLoginInfoService.updateUserLoginInfo(loginInfoDto);
            }
        }

        // 校验密码
        if(!infoDto.getPassword().equals(EncryptUtil.SHA1(request.getPass_word(), infoDto.getPasssalt()))) {
            response.setRetcode(UserLoginInfoErrorCode.USER_LOGIN_ERROR);
            response.setMessage("账户或密码不正确");
            loginInfoDto.setErrorCount(infoDto.getErrorCount() + 1); // 错误次数加1
            if(loginInfoDto.getErrorCount() > 3) { // TODO 后面改成系统常量设置
                loginInfoDto.setState(UserLoginInfoConstant.State.LOCKED);
                loginInfoDto.setLockTime(DateTimeUtils.currentDate());
            }
            userLoginInfoService.updateUserLoginInfo(loginInfoDto);
            return response;
        }
        response.setData(userLoginAuxiliary.createToken(infoDto, false, servletRequest));
        return response;
    }

    @RecordLogger
    @RequestMapping(value = "getToken", method = RequestMethod.POST)
    public Response getToken(HttpServletRequest servletRequest) {
        DataResponse response = new DataResponse();
        response.setData(userLoginAuxiliary.createToken(null, true, servletRequest));
        return response;
    }
}