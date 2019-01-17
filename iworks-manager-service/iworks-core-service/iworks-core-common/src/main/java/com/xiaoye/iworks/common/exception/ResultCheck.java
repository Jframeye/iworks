package com.xiaoye.iworks.common.exception;

import com.xiaoye.iworks.api.result.Response;

/**
 * 功能描述: 结果检查类
 * @auther: yehl
 * @date: 2019/1/17 13:36
 */
public class ResultCheck {

    /**
     * 功能描述: 结果校验
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/2 19:51
     */
    public void check(Response response) {
        if(!"0".equalsIgnoreCase(response.getRetcode())) {
            throw new BizServiceException(response.getRetcode(), response.getMessage());
        }
    }

    /**
     * 功能描述: 结果校验
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/2 19:51
     */
    public boolean checkSilent(Response response) {
        return "0".equalsIgnoreCase(response.getRetcode());
    }
}
