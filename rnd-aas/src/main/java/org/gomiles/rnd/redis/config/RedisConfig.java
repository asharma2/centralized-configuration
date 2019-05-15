package org.gomiles.rnd.redis.config;

import org.gomiles.rnd.redis.model.Restaurant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories(basePackages = "org.gomiles.rnd.redis.repository")
public class RedisConfig {

	@Value("${spring.redis.host}")
	String host;
	
	@Value("${spring.redis.port}")
	int port;
	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		// JedisPoolConfig poolConfig = new JedisPoolConfig();
		// poolConfig.setMaxTotal(5);
		// poolConfig.setTestOnBorrow(true);
		// poolConfig.setTestOnReturn(true);
		//
		// RedisClusterConfiguration configuration = new RedisClusterConfiguration();
		// configuration.setClusterNodes(Arrays.asList(new RedisNode("localhost",
		// 6379)));
		//
		// JedisConnectionFactory connectionFactory = new
		// JedisConnectionFactory(configuration, poolConfig);
		//
		// return connectionFactory;

		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
		return new JedisConnectionFactory(config);
	}

	// @Bean
	// public LettuceConnectionFactory redisConnectionFactory() {
	// return new LettuceConnectionFactory(new
	// RedisStandaloneConfiguration("localhost", 6379));
	// }

	@Bean
	public RedisTemplate<String, Restaurant> redisTemplate() {
		RedisTemplate<String, Restaurant> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setEnableTransactionSupport(true);
		return redisTemplate;
	}

	@Bean
	public StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory());
		stringRedisTemplate.setEnableTransactionSupport(true);
		return stringRedisTemplate;
	}
}
