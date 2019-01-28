package com.xiaoye.iworks.common.support;

import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.common.exception.BizServiceException;

/**
 * 功能描述: 结果检查类
 * @auther: yehl
 * @date: 2019/1/25 19:39
 */
public class ResponseCheck {

    /**
     * 功能描述: 结果校验
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/25 19:40
     */
    public static void checkSuccess(Response response) {
        if(!"0".equals(response.getRetcode())) {
            throw new BizServiceException(response.getRetcode(), response.getMessage());
        }
    }

    /**
     * 功能描述: 
     * @param:
     * @return: 
     * @auther: yehl
     * @date: 2019/1/25 19:42
     */
    public static boolean checkSilent(Response response) {
        if(!"0".equals(response.getRetcode())) {
            return false;
        }
        return true;
    }
}
