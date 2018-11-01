package com.xiaoye.iworks.basic.api;

import com.xiaoye.iworks.basic.api.input.email.EmailQueryInput;
import com.xiaoye.iworks.core.api.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮件服务接口
 * @author yehl
 * @date 2018/11/1 17:08
 */
@RestController
@RequestMapping(value = "api/emailservice")
public class EmailController {

    /** 
     * 查找邮件设置信息
     * @param 
     * @return 
     * @author yehl
     * @date 2018/11/1 18:11
     */
    @RequestMapping(value = "findEmailInfo", method = { RequestMethod.POST, RequestMethod.GET })
    public Result findEmailInfo(EmailQueryInput input) {
        // TODO
        return Result.success();
    }

    /** 
     * 更新邮件设置信息
     * @param 
     * @return 
     * @author yehl
     * @date 2018/11/1 18:14
     */
    @RequestMapping(value = "updateEmailInfo", method = RequestMethod.POST)
    public Result updateEmailInfo(EmailQueryInput input) {
        // TODO
        return Result.success();
    }

    /** 
     * 发送邮件
     * @param 
     * @return 
     * @author yehl
     * @date 2018/11/1 18:16
     */
    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    public Result sendEmail(EmailQueryInput input) {
        // TODO
        return Result.success();
    }
}
