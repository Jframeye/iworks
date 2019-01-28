package com.xiaoye.iworks.common.session.token;

import com.xiaoye.iworks.service.RedisCacheService;
import com.xiaoye.iworks.utils.CollectionUtils;
import com.xiaoye.iworks.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    /** token过期时间 **/
    private final Long tokenExpirationTime = 15L;
    /** refresh token过期时间 **/
    private final Long refreshTokenExpirationTime = 60L;
    private final String tokenIssuer = "http://clearworld.top";
    /** 密钥 **/
    private final String tokenSigningKey = "xm8EV6Hy5RMFK4EEACIDAwQus";

    @Autowired
	private RedisCacheService redisCacheService;

	/**
	 * 创建token
	 * @param token
	 * @param refresh
	 * @return
	 */
	public String createToken(Token token, boolean refresh) {
		if (StringUtils.hasEmptyString(token.getUser_no(), token.getUser_name()))
			throw new IllegalArgumentException("Cannot create JWT Token without user_no and user_name");

		Claims claims = Jwts.claims().setId(refresh ? "refresh" : "").setSubject(token.getUser_no());
		claims.put("username", token.getUser_name());
		if (CollectionUtils.isNotEmpty(token.getAuthorities())) {
			claims.put("scopes", token.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));
		}

		LocalDateTime currentTime = LocalDateTime.now();

		String str = Jwts.builder().setClaims(claims)
				.setIssuer(tokenIssuer)
				.setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
				.setExpiration(Date.from(currentTime.plusMinutes(refresh ? refreshTokenExpirationTime
								: tokenExpirationTime).atZone(ZoneId.systemDefault()).toInstant()))
				.signWith(SignatureAlgorithm.HS512, tokenSigningKey).compact();

		// 保存到缓存中【token ip】
		redisCacheService.put(str, token.getIp(), tokenExpirationTime);
		return str;
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
