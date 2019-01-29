package com.xiaoye.iworks.common.session;

import com.xiaoye.iworks.common.constant.SessionConstant;
import com.xiaoye.iworks.common.exception.BizServiceException;
import com.xiaoye.iworks.common.session.annotation.CheckSession;
import com.xiaoye.iworks.common.session.token.Token;
import com.xiaoye.iworks.common.session.token.TokenFactory;
import com.xiaoye.iworks.common.support.SpringContextHolder;
import com.xiaoye.iworks.service.RedisCacheService;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.utils.WebUtils;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 功能描述: session 工具
 * @auther: yehl
 * @date: 2019/1/28 10:11
 */
public class SessionUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(SessionUtils.class);

    private static TokenFactory tokenFactory;
    private static RedisCacheService redisCacheService;

    static {
        tokenFactory = SpringContextHolder.getBean(TokenFactory.class);
        redisCacheService = SpringContextHolder.getBean(RedisCacheService.class);
    }

    public static Token validate(CheckSession checkSession, HttpServletRequest request) {
        // 获取请求头中的token
        String str = tokenFactory.checkToken(request);
        Token token;
        try {
            // 解析token
            Jws<Claims> claims = tokenFactory.parseToken(str);

            String user_pkid = claims.getBody().getId();
            String user_no = claims.getBody().getSubject();
            String nick_name = claims.getBody().get("nickname", String.class);
            String user_name = claims.getBody().get("username", String.class);
            token = new Token(user_pkid, user_no, nick_name, user_name);
            List<String> scopes = claims.getBody().get("scopes", List.class);

            // 防止token被盗用, IP不一样必须重新登录
            String token_cache_ip = redisCacheService.getString(str);
            String ip = String.format("%s_%s", token.getUser_pkid(), WebUtils.getRequestIp(request));
            if(!token_cache_ip.equals(ip)) {
                LOGGER.error(ServiceErrorCode.INVALID_LOGIN_ERROR, "登录失效【Invalid authorization header size】");
                throw new BizServiceException(ServiceErrorCode.INVALID_LOGIN_ERROR, "登录失效，请重新登录");
            }
            // 判断与缓存中的token是否一致
            String key = String.format("login_key_%s", token.getUser_no());
            String token_str = redisCacheService.getString(key);
            if(!str.equals(token_str)) { // 传进来的token与缓存中的token不一致
                LOGGER.error(ServiceErrorCode.INVALID_LOGIN_ERROR, "登录失效【请求的token与缓存中的token不一致】");
                throw new BizServiceException(ServiceErrorCode.INVALID_LOGIN_ERROR, "登录失效，请重新登录");
            }

            String permission = checkSession.permission();
            if(StringUtils.isNotBlank(permission)) {
                String hit = CollectionUtils.firstMatched(scopes, (auth) -> permission.equals(auth));
                if(StringUtils.isBlank(hit)) {
                    throw new BizServiceException(ServiceErrorCode.NO_PERMISSION_ERROR, "无操作权限");
                }
            }
        } catch (ExpiredJwtException e) {
            throw new BizServiceException(ServiceErrorCode.EXPIRED_LOGIN_ERROR, "登录过期");
        } catch (Exception e) {
            throw new BizServiceException(ServiceErrorCode.INVALID_LOGIN_ERROR, "登陆失效");
        }
        return token;
    }
}
