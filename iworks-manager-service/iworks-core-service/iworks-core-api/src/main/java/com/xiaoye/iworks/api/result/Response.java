package com.xiaoye.iworks.api.result;

import com.xiaoye.iworks.common.exception.BizServiceException;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 功能描述: 响应结果
 * @auther: yehl
 * @date: 2019/1/2 19:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Response implements Serializable {
    private static final long serialVersionUID = -3310502415125126837L;

    private String retcode = "0";
    private String message = "success";

    private String attach;

    public Response() {}

    public Response(String retcode, String message) {
        this.retcode = retcode;
        this.message = message;
    }

    /**
     * 功能描述: 结果校验
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/2 19:51
     */
    public void check() {
        if(!"0".equalsIgnoreCase(this.getRetcode())) {
            throw new BizServiceException(this.getRetcode(), this.getMessage());
        }
    }

    /**
     * 功能描述: 结果校验
     * @param:
     * @return:
     * @auther: yehl
     * @date: 2019/1/2 19:51
     */
    public boolean checkSilent() {
        return "0".equalsIgnoreCase(this.getRetcode());
    }
}
