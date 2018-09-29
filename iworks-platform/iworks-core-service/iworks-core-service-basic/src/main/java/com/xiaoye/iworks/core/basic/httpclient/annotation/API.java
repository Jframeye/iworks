/**
 * 
**/
package com.xiaoye.iworks.core.basic.httpclient.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xiaoye.iworks.core.basic.httpclient.support.ApiInvokMethod;

/**
 * 外调API接口注解
 * @author yehl
 * @date 2018年6月23日
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface API {

	/**
	 * 访问地址
	 * @return
	 */
	String url() default "";
	
	/**
	 * 编码格式
	 * @return
	 */
	String charset() default "UTF-8";
	
	/**
	 * 接口调用方式
	 * @return
	 */
	ApiInvokMethod method() default ApiInvokMethod.POST;
	
	/**
	 * 属性名称是否由驼峰转为下划线
	 * @return
	 */
	boolean camel2Underline() default true;
}
