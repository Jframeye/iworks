package com.xiaoye.iworks.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.xiaoye.iworks.utils.StringUtils;
import com.xiaoye.iworks.utils.exception.ServiceErrorCode;
import com.xiaoye.iworks.utils.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * @Desc Redis 缓存操作
 * @Author yehl
 * @Date 2018年2月2日
 */
@Service
public class RedisCacheService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public boolean setnx(String key, String value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	public boolean setnx(String key, Object value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	public void put(String key, String value) {
		this.put(key, value, 0L);
	}

	public void put(String key, String value, long expired) {
		this.set(key, value, expired);
	}

	public void put(String key, Object value) {
		this.put(key, value, 0L);
	}

	public void put(String key, Object value, long expired) {
		this.set(key, JSON.toJSONString(value), expired);
	}

	public Object get(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public String getString(String key) {
		Object object = redisTemplate.opsForValue().get(key);
		if (object == null) return null;
		return object.toString();
	}

	public <T> T getBean(String key, Class<T> clazz) {
		Object object = redisTemplate.opsForValue().get(key);
		if (object == null) return null;
		return JSON.parseObject(object.toString(), clazz);
	}

	public <T> List<T> getList(String key, Class<T> clazz) {
		Object object = redisTemplate.opsForValue().get(key);
		if (object == null) return null;
		return JSON.parseArray(object.toString(), clazz);
	}

	public void delete(String... keys) {
		redisTemplate.delete(Arrays.asList(keys));
	}

	@SuppressWarnings("unchecked")
	public <K, V> Map<K, V> getMap(String key) {
		Object object = redisTemplate.opsForValue().get(key);
		if (object == null) return null;
		return JSON.parseObject(object.toString(), Map.class);
	}

	/**
	 * 保存到redis
	 * @param key
	 * @param value
	 * @param expired
	 */
	private void set(String key, String value, long expired) {
		if (StringUtils.isEmpty(key))
			throw new ServiceException(ServiceErrorCode.PARAM_ERROR, "缓存键值不能为空");
		if (redisTemplate.hasKey(key)) {
			redisTemplate.delete(key);
		}
		redisTemplate.opsForValue().set(key, value, expired);
	}
}
