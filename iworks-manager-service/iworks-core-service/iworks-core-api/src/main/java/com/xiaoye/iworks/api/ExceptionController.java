/**
 * 
**/
package com.xiaoye.iworks.api;

import com.xiaoye.iworks.api.result.Response;
import com.xiaoye.iworks.common.exception.BizExceptionCode;
import com.xiaoye.iworks.common.exception.BizServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * @author yehl
 * @date 2018年6月23日
 */
@ControllerAdvice
public class ExceptionController extends BasicController {
    
    @ResponseBody
    @ExceptionHandler(value = BizServiceException.class)
    public Response exceptionHandler(HttpServletRequest request, BizServiceException exception) {
    	return new Response(exception.getCode(), exception.getMessage());
    }
    
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(HttpServletRequest request, Exception exception) {
    	return new Response(BizExceptionCode.APP_ERROR, exception.getMessage());
    }
}
