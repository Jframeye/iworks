package com.xiaoye.iworks.core.basic.cache.redis;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.xiaoye.iworks.core.basic.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * @Desc Redis 缓存操作
 * @Author yehl
 * @Date 2018年2月2日
 */
@Service("RedisCacheService")
public class RedisCacheServiceImpl implements CacheService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean setIfAbsent(String key, Object value) {
		return redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	@Override
	public void put(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void put(String key, Object value, long expired) {
		redisTemplate.opsForValue().set(key, value, expired);
	}

	@Override
	public Object getData(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public <T> T getData(String key, Class<T> clazz) {
		Object object = this.getData(key);
		if(object == null) return null;
		return JSON.parseObject(Objects.toString(object), clazz);
	}

	@Override
	public <T> List<T> getDataList(String key, Class<T> clazz) {
		Object object = this.getData(key);
		if(object == null) return null;
		return JSON.parseArray(Objects.toString(object), clazz);
	}

	@Override
	public boolean expired(String key, long timeout, TimeUnit unit) {
		return redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public boolean expiredAt(String key, Date date) {
		return redisTemplate.expireAt(key, date);
	}

	@Override
	public boolean exist(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public long delete(String ...keys) {
		if(keys != null && keys.length > 0) {
			return redisTemplate.delete(Arrays.asList(keys));
		}
		return 0L;
	}
}
