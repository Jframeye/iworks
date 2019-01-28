package com.xiaoye.iworks.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能描述: WEB相关工具类
 * @auther: yehl
 * @date: 2019/1/28 16:34
 */
public class WebUtils {

    /**
     * 功能描述: 获取访问的IP地址
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/28 16:36
     */
    public static String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 功能描述: 
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/28 16:42
     */
    public static String getBrowerName(HttpServletRequest request) {
        return null;
    }
}
