/**
 * 
**/
package com.xiaoye.iworks.core.basic.cache.lock;

import java.lang.reflect.Method;
import java.util.Objects;

import com.xiaoye.iworks.core.basic.cache.CacheService;
import com.xiaoye.iworks.core.basic.support.SpringContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author yehl
 * @date 2018年7月12日 下午4:46:00
 */
@Aspect
@Component
public class LockAspect {

	@Around("@annotation(com.xiaoye.microservices.basic.cache.lock.Lock)")
	public Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Lock lock = null;
		if((lock = getLock(joinPoint)) != null) {
			// 获取加锁方式
			String lockWay = lock.lockWay();
			if(Objects.equals(LockWays.LOCAL_LOCK, lockWay)) { // 本地锁
				// TODO
				return joinPoint.proceed(); // 执行目标方法
			} else { // 缓存锁
				CacheService cacheService = SpringContextHolder.getBean(lockWay, CacheService.class);
				if(cacheService == null) {
					throw new RuntimeException(String.format("获取%s缓存实例失败", lockWay));
				}
				if(cacheService.setIfAbsent(lock.key(), "lock")) {
					return joinPoint.proceed(); // 执行目标方法
				} else {
					throw new RuntimeException("获取锁失败");
				}
			}
		} else {
			return joinPoint.proceed(); // 执行目标方法
		}
	}
	
	/**
	 * 获取方法上的锁注解
	 * @param joinPoint
	 * @return
	 */
	private Lock getLock(JoinPoint joinPoint) {
		if(joinPoint == null) return null;
		Signature signature = joinPoint.getSignature();
		if(signature == null) return null;
		Method method = ((MethodSignature) signature).getMethod();
		return method.getAnnotation(Lock.class);
	}
}
