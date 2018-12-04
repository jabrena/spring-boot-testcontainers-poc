package org.jab.microservices.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Bean
	@ConfigurationProperties(prefix = "cache.redis")
	GlobalConfiguration config(){
		return new GlobalConfiguration();
	}

	@Bean
	public JedisPoolConfig jedisPoolConfig(GlobalConfiguration config) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(Integer.parseInt(config.getMaxActive()));
		jedisPoolConfig.setMinIdle(Integer.parseInt(config.getMinIdle()));
		jedisPoolConfig.setMaxIdle(Integer.parseInt(config.getMaxIdle()));
		jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(config.getMaxWait()));
		jedisPoolConfig.setMaxWaitMillis(Long.parseLong(config.getMaxWait()));
		return jedisPoolConfig;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory(GlobalConfiguration config, JedisPoolConfig jedisPoolConfig) {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(config.getHost());
		redisStandaloneConfiguration.setDatabase(0);
		redisStandaloneConfiguration.setPort(Integer.parseInt(config.getPort()));
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
				(JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
		jpcb.poolConfig(jedisPoolConfig);
		JedisClientConfiguration jedisClientConfiguration = jpcb.build();
		return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
	}

	@Bean
	public RedisCacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {
		return RedisCacheManager.create(jedisConnectionFactory);
	}

	@Bean
	public RedisTemplate<String, String> redisTmplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}

	/*
	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

		StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
		return template;
	}
	*/


}