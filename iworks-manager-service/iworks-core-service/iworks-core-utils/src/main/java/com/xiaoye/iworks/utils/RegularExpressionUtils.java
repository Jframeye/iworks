/**
 * 
**/
package com.xiaoye.iworks.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * @author yehl
 * @date 2018年6月23日
 */
public class RegularExpressionUtils {

	/**
	 * 查找{@code str}符合正则表达式{@code regex}的字符串<br>
	 * @param regex
	 * @param str
	 * @return
	 */
	public static List<String> find(String regex, String str) {
		Matcher matcher = Pattern.compile(regex).matcher(str);
		List<String> list = new ArrayList<String>();
		while (matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}
}
