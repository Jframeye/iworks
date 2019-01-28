package com.xiaoye.iworks.common.session.token;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 功能描述: 授权信息
 * @auther: yehl
 * @date: 2019/1/28 10:17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Token {

	/** 用户编码 **/
	private final String user_no;
	/** 用户账号 **/
	private final String user_name;
	/** 权限信息 **/
	private List<?> authorities;
	/** 用户登录IP **/
	private String ip;
}
