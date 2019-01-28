package com.xiaoye.iworks.common.session;

import com.xiaoye.iworks.common.constant.SessionConstant;
import com.xiaoye.iworks.common.exception.BizServiceException;
import com.xiaoye.iworks.common.session.annotation.CheckSession;
import com.xiaoye.iworks.common.session.token.TokenFactory;
import com.xiaoye.iworks.common.support.SpringContextHolder;
import com.xiaoye.iworks.service.RedisCacheService;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.utils.WebUtils;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 功能描述: session 工具
 * @auther: yehl
 * @date: 2019/1/28 10:11
 */
public class SessionUtils {

    private static TokenFactory tokenFactory;
    private static RedisCacheService redisCacheService;

    static {
        tokenFactory = SpringContextHolder.getBean(TokenFactory.class);
        redisCacheService = SpringContextHolder.getBean(RedisCacheService.class);
    }

    public static void validate(CheckSession checkSession, HttpServletRequest request) {
        String header = request.getHeader(SessionConstant.AUTH_HEADER_NAME);
        if (StringUtils.isEmpty(header)) {
            throw new BizServiceException(ServiceErrorCode.PARAM_ERROR, "Authorization header cannot be blank!");
        }

        if (header.length() < SessionConstant.AUTH_HEADER_PREFIX.length()) {
            throw new BizServiceException(ServiceErrorCode.PARAM_ERROR, "Invalid authorization header size.");
        }
        // 获取请求头中的token
        String token = header.substring(SessionConstant.AUTH_HEADER_PREFIX.length(), header.length());
        try {
            // 解析token
            Jws<Claims> claims = tokenFactory.parseToken(token);
            String refresh = claims.getBody().getId();
            if (StringUtils.isNotEmpty(refresh)) {
                throw new BizServiceException(ServiceErrorCode.AUTH_ERROR);
            }

            String token_cache_ip = redisCacheService.getString(token);
            String ip = WebUtils.getRequestIp(request);
            if(!token_cache_ip.equals(ip)) {
                redisCacheService.delete(token); // 删除token缓存
                throw new BizServiceException(ServiceErrorCode.AUTH_ERROR, "登录失效，请重新登录");
            }

            String user_no = claims.getBody().getSubject();
            String user_name = claims.getBody().get("username", String.class);
            List<?> scopes = claims.getBody().get("scopes", List.class);

            String permission = checkSession.permission();
            if(StringUtils.isNotBlank(permission)) {
                // TODO 权限校验
            }
        } catch (ExpiredJwtException e) {
            throw new BizServiceException(ServiceErrorCode.AUTH_ERROR, "token过期");
        } catch (Exception e) {
            throw new BizServiceException(ServiceErrorCode.AUTH_ERROR, "token异常");
        }
    }
}
