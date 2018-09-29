/**
 * 
**/
package com.xiaoye.iworks.core.basic.cache.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁注解
 * @author yehl
 * @date 2018年7月12日 下午1:04:14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Lock {

	/**
	 * 唯一标识
	 * @return
	 */
	String key();
	
	/**
	 * 超时时间，默认不超时
	 * @return
	 */
	long timeout() default 0L;
	
	/**
	 * 超时时间单位，默认秒
	 * @return
	 */
	TimeUnit timeUnit() default TimeUnit.SECONDS;
	
	/**
	 * 加锁方式
	 * @return
	 */
	String lockWay() default LockWays.LOCAL_LOCK;
}
