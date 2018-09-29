/**
 * 
**/
package com.xiaoye.iworks.core.basic.cache;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存接口类
 * @author yehl
 * @date 2018年7月12日 下午1:07:30
 */
public interface CacheService {

	/**
	 * 当key不存在时，set value值，返回true；
	 * 当key存在时，直接返回false
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setIfAbsent(String key, Object value);
	
	/**
	 * 缓存数据
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value);
	
	/**
	 * 缓存数据
	 * @param key
	 * @param value
	 * @param expired
	 */
	public void put(String key, Object value, long expired);
	
	/**
	 * 获取缓存值
	 * @param key
	 * @return
	 */
	public Object getData(String key);
	
	/**
	 * 获取缓存值
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T getData(String key, Class<T> clazz);
	
	/**
	 * 获取缓存值
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> List<T> getDataList(String key, Class<T> clazz);
	
	/**
	 * 设置过期时间
	 * @param key
	 * @param timeout
	 * @param timeUnit
	 */
	public boolean expired(String key, long timeout, TimeUnit timeUnit);
	
	/**
	 * 设置什么时候过期
	 * @param key
	 * @param date
	 */
	public boolean expiredAt(String key, Date date);
	
	/**
	 * 判断key是否已缓存
	 * @param key
	 * @return
	 */
	public boolean exist(String key);
	
	/**
	 * 删除缓存
	 * @param keys
	 * @return
	 */
	public long delete(String ...keys);
}
