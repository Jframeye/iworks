/**
 * 
**/
package com.xiaoye.iworks.core.api;

import com.xiaoye.iworks.core.api.result.Result;
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
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception exception) {
    	return Result.failure(0, exception.getMessage());
    }
}
