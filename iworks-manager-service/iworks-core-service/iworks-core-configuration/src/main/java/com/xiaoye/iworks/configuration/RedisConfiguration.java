package com.xiaoye.iworks.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Desc Redis 缓存配置
 * @Author yehl
 * @Date 2018年2月2日
 */
@Configuration
public class RedisConfiguration {

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setKeySerializer(new StringRedisSerializer()); // 序列化键值
		template.setValueSerializer(new StringRedisSerializer()); // 序列化值
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}