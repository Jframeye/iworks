/**
 * 
 */
package com.xiaoye.iworks.common.logger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 记录输出日志
 * @author yehl
 * @date 2018年4月21日 上午9:48:49
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RecordLogger {

	/**
	 * 输入日志
	 * @return
	 */
	boolean inputLogger() default true;
	
	/**
	 * 输出日志
	 * @return
	 */
	boolean resultLogger() default true;
	
	/**
	 * 保存输入日志
	 * @return
	 */
	boolean recordInput() default false;
	
	/**
	 * 保存输出日志
	 * @return
	 */
	boolean recordResult() default false;
}
