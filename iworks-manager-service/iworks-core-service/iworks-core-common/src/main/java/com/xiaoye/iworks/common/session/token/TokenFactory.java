package com.xiaoye.iworks.common.session.token;

import com.xiaoye.iworks.common.constant.SessionConstant;
import com.xiaoye.iworks.common.exception.BizServiceException;
import com.xiaoye.iworks.service.RedisCacheService;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Desc TODO
 * @Author yehl
 * @Date 2018年1月24日
 */
@Component
public class TokenFactory {

	private Logger LOGGER = LoggerFactory.getLogger(getClass());

    /** token过期时间 **/
    private final Long tokenExpirationTime = 15L;
    private final String tokenIssuer = "http://clearworld.top";
    /** 密钥 **/
    private final String tokenSigningKey = "xm8EV6Hy5RMFK4EEACIDAwQus";

    @Autowired
	private RedisCacheService redisCacheService;

	/**
	 * 创建token
	 * @param token
	 * @return
	 */
	public String createToken(Token token) {
		if (StringUtils.hasEmptyString(token.getUser_pkid(), token.getUser_no(), token.getNick_name(), token.getUser_name())) {
			throw new BizServiceException(ServiceErrorCode.APP_ERROR, "token 创建失败");
		}

		Claims claims = Jwts.claims().setId(token.getUser_pkid()).setSubject(token.getUser_no());
		claims.put("nickname", token.getNick_name());
		claims.put("username", token.getUser_name());
		if (CollectionUtils.isNotEmpty(token.getAuthorities())) {
			claims.put("scopes", token.getAuthorities());
		}

		LocalDateTime currentTime = LocalDateTime.now();

		String str = Jwts.builder().setClaims(claims)
				.setIssuer(tokenIssuer)
				.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(Date.from(currentTime.plusMinutes(tokenExpirationTime).atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS512, tokenSigningKey).compact();

		// 保存到缓存中【token ip】
		String key = String.format("login_key_%s", token.getUser_no());
		String token_cache = redisCacheService.getString(key);
		if(StringUtils.isNotBlank(token_cache)) {
			redisCacheService.delete(token_cache); // 删除缓存的IP关系
		}
		redisCacheService.put(key, str, tokenExpirationTime * 2);
		redisCacheService.put(str, String.format("%s_%s", token.getUser_pkid(), token.getIp()), tokenExpirationTime * 2); // 设置当前登录的IP地址
		return str;
	}

	/**
	 * 功能描述: 校验并获取token
	 * @param:
	 * @return: 
	 * @auther: yehl
	 * @date: 2019/1/29 15:54
	 */
	public String checkToken(HttpServletRequest request) {
		String header = request.getHeader(SessionConstant.AUTH_HEADER_NAME);
		if (StringUtils.isEmpty(header)) {
			LOGGER.error(ServiceErrorCode.INVALID_LOGIN_ERROR, "无效请求【Authorization header cannot be blank】");
			throw new BizServiceException(ServiceErrorCode.INVALID_LOGIN_ERROR, "无效请求");
		}

		if (header.length() < SessionConstant.AUTH_HEADER_PREFIX.length()) {
			LOGGER.error(ServiceErrorCode.INVALID_LOGIN_ERROR, "无效请求【Invalid authorization header size】");
			throw new BizServiceException(ServiceErrorCode.INVALID_LOGIN_ERROR, "无效请求");
		}
		// 获取请求头中的token
		return header.substring(SessionConstant.AUTH_HEADER_PREFIX.length(), header.length());
	}

	/**
	 * 解析token
	 * @param token
	 * @return
	 */
	public Jws<Claims> parseToken(String token) {
		return Jwts.parser().setSigningKey(tokenSigningKey).parseClaimsJws(token);
	}
}
