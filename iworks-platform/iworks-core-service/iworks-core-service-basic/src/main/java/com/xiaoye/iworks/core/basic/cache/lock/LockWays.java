/**
 * 
**/
package com.xiaoye.iworks.core.basic.cache.lock;

/**
 * 锁方式
 * @author yehl
 * @date 2018年7月12日 下午1:48:40
 */
public class LockWays {

	/** 本地锁 **/
	public static final String LOCAL_LOCK = "Local";
	
	/** Redis 锁 **/
	public static final String REDIS_LOCK = "RedisCacheService";
	
	/** Memcached 锁 **/
	public static final String MEMCACHED_LOCK = "MemcachedCacheService";
}
