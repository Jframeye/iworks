package com.xiaoye.iworks.utils;

import java.util.Date;
import java.util.UUID;

/**
 * 字符串工具类
 * @author yehl
 * @date 2018年6月23日
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	/** 编码类型：utf-8 */
	public static final String UTF = "UTF-8";
	
	/** 编码类型：GBK */
	public static final String GBK = "GBK";

    /**
     * 首字母小写
     * @param str
     * @return
     */
	public static String firstToLowerCase(String str){
	    if(isEmpty(str)) throw new IllegalArgumentException("the argument can not be null or ''");
		return str.replaceFirst(str.substring(0, 1),str.substring(0, 1).toLowerCase());
	}
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String firstToUpperCase(String str){
	    if(isEmpty(str)) throw new IllegalArgumentException("the argument can not be null or ''");
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}

    /**
	 * 描述: 获取{@link length} 长度的随机数字<br>
	 * @param length
	 * @return
	 */
	public static String randomNumber(int length) {
		return String.valueOf(Math.random()).substring(2, length + 2);
	}
	
	/**
	 * 描述: 32位长度的UUID <br>
	 * @return
	 */
	public static String UUID32() {
		return UUID36().replaceAll("-", "");
	}
	
	/**
	 * 描述: 36位长度的UUID <br>
	 * @return
	 */
	public static String UUID36() {
		return UUID.randomUUID().toString().toUpperCase();
	}

    /**
	 * 下划线转驼峰法 (忽略开始的"_")
	 * @param str 源字符串
	 * @return 转换后的字符串
	 */
	public static String underline2Camel(String str) {
		if(isEmpty(str)) return "";
		while(str.startsWith("_")) {
			str = str.substring(1);
		}
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if ("_".charAt(0) == ch) {
                flag = true;
            } else {
                if (flag) {
                	sb.append(Character.toUpperCase(ch));
                    flag = false;
                } else {
                	sb.append(ch);
                }
            }
        }
        return sb.toString();
    }
    
	/**
	 * 驼峰法转下划线（首字母大写直接转为小写）
	 * @param str 源字符串
	 * @return 转换后的字符串
	 */
	public static String camel2Underline(String str) {
		if(isEmpty(str)) return "";
		str = String.valueOf(str.charAt(0)).toLowerCase().concat(str.substring(1));
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(Character.isUpperCase(ch)) {
            	sb.append("_").append(Character.toLowerCase(ch));
            } else {
            	sb.append(ch);
            }
        }
		return sb.toString();
    }

	/**
	 * 获取字符串值
	 * @param value
	 * @return
	 */
	public static String getStringValue(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Integer) {
			return ((Integer) value).intValue() + "";
		} else if (value instanceof String) {
			return value.toString();
		} else if (value instanceof Double) {
			return ((Double) value).doubleValue() + "";
		} else if (value instanceof Float) {
			return ((Float) value).floatValue() + "";
		} else if (value instanceof Long) {
			return ((Long) value).longValue() + "";
		} else if (value instanceof Boolean) {
			return ((Boolean) value).booleanValue() ? "1" : "0";
		} else if (value instanceof Date) {
			return DateTimeUtils.formatDateToString((Date) value);
		} else {
			throw new IllegalArgumentException("the value is not the basic data type.");
		}
	}

	/**
	 * 根据regex 分割字符串为数组
	 * @param string
	 * @param regex
	 * @return
	 */
	public static String[] toArray(String string, String regex) {
		if (isEmpty(string)) return null;
		if (isEmpty(regex)) return new String[] { string };
		return string.split(regex);
	}
	
	/**
     * 判断字符串数组存在空值
     * @param strs
     * @return if has empty value return true; else return false.
     */
    public static boolean hasEmptyString(String...strs) {
        if(strs == null) return true;
        for (String string : strs) {
            if(isEmpty(string)) {
                return true;
            }
        }
        return false;
    }
    
    /**
	 * 判断字符串数组不存在空值
	 * @param strs
	 * @return if has empty value return false; else return true.
	 */
	public static boolean hasNoEmptyString(String...strs) {
	    return !hasEmptyString(strs);
	}

	/**
     * 判断字符串数组不存在空值
     * @param strs
     * @return if has empty value return false; else return true.
     */
    public static boolean hasNoBlankString(String...strs) {
        return !hasBlankString(strs);
    }

	/**
     * 判断字符串数组存在空值
     * @param strs
     * @return if has empty value return true; else return false.
     */
    public static boolean hasBlankString(String...strs) {
        if(strs == null) return true;
        for (String string : strs) {
            if(isBlank(string)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断字符串是否不为空
     * <pre>
     * StringUtils.isNotEmpty(null)          = false
     * StringUtils.isNotEmpty("")            = false
     * StringUtils.isNotEmpty("     ")       = false
     * StringUtils.isNotEmpty("abc")         = true
     * StringUtils.isNotEmpty("    abc    ") = true
     * </pre>
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 判断字符串是否不为空
     * <pre>
     * StringUtils.isNotEmpty(null)          = false
     * StringUtils.isNotEmpty("")            = false
     * StringUtils.isNotEmpty("     ")       = true
     * StringUtils.isNotEmpty("abc")         = true
     * StringUtils.isNotEmpty("    abc    ") = true
     * </pre>
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
