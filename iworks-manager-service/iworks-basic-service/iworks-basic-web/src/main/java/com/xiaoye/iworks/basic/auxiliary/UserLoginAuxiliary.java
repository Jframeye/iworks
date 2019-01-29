package com.xiaoye.iworks.basic.auxiliary;

import com.xiaoye.iworks.api.result.DataResponse;
import com.xiaoye.iworks.basic.api.UserLoginInfoService;
import com.xiaoye.iworks.basic.api.dto.UserLoginInfoDto;
import com.xiaoye.iworks.basic.api.input.UserLoginInfoQueryInput;
import com.xiaoye.iworks.common.exception.BizServiceException;
import com.xiaoye.iworks.common.session.token.Token;
import com.xiaoye.iworks.common.session.token.TokenFactory;
import com.xiaoye.iworks.common.support.ResponseCheck;
import com.xiaoye.iworks.service.RedisCacheService;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.utils.WebUtils;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: 用户登录辅助类
 * @auther: yehl
 * @date: 2019/1/28 16:44
 */
@Service
public class UserLoginAuxiliary {

    @Autowired
    private TokenFactory tokenFactory;
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private UserLoginInfoService userLoginInfoService;

    /**
     * 功能描述: 创建Token
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/29 16:20
     */
    public String createToken(UserLoginInfoDto infoDto, boolean refresh, HttpServletRequest request) {
        if(refresh) {
            String token_str = tokenFactory.checkToken(request);
            if(StringUtils.isBlank(token_str)) {
                throw new BizServiceException(ServiceErrorCode.INVALID_LOGIN_ERROR, "登录失效，请重新登录");
            }
            // 获取缓存中的IP
            String token_cache_ip = redisCacheService.getString(token_str);
            // 获取user_pkid
            Long pkid = Long.parseLong(token_cache_ip.split("_")[0]);
            String cache_ip = token_cache_ip.split("_")[1];
            String ip = WebUtils.getRequestIp(request);
            if(!cache_ip.equals(ip)) {
                throw new BizServiceException(ServiceErrorCode.INVALID_LOGIN_ERROR, "登录失效，请重新登录");
            }
            UserLoginInfoQueryInput input = new UserLoginInfoQueryInput();
            input.setPkid(pkid);
            input.setCheckNull(true);
            DataResponse<UserLoginInfoDto> dataResponse = userLoginInfoService.findUserLoginInfo(input);
            ResponseCheck.checkSuccess(dataResponse);
            infoDto = dataResponse.getData();
        }
        // 生成token
        Token token = new Token(String.valueOf(infoDto.getPkid()), infoDto.getUserNo(), infoDto.getNickName(), infoDto.getUserName());
        token.setIp(WebUtils.getRequestIp(request));
        // TODO 查询权限集并设置
        return tokenFactory.createToken(token);
    }
}
