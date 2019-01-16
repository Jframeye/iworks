/**
 * 
**/
package com.xiaoye.iworks.utils;

/**
 * 正则表达式
 * @author yehl
 * @date 2018年6月23日
 */
public class RegularExpression {

	/** 匹配 ${} 格式 **/
	public static final String $_ = "(?<=(?<!\\\\)\\$\\{)(.*?)(?=(?<!\\\\)\\})";
}
