package com.wxy.spring_boot.config.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;

//@Configuration
//@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	/*
	 * @Bean public CacheManager
	 * cacheManager(@SuppressWarnings("rawtypes")RedisTemplate redisTemplate) {
	 * 
	 * RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
	 * cacheManager.setDefaultExpiration(3000); return cacheManager; }
	 */
	/*@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		// 初始化一个RedisCacheWriter
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
		// 设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
		// ClassLoader loader = this.getClass().getClassLoader();
		// JdkSerializationRedisSerializer jdkSerializer = new
		// JdkSerializationRedisSerializer(loader);
		// RedisSerializationContext.SerializationPair<Object> pair =
		// RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
		// RedisCacheConfiguration
		// defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();
		// 设置默认超过期时间是30秒
		defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
		// 初始化RedisCacheManager
		RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
		return cacheManager;
	}

	@Override
	public CacheResolver cacheResolver() {

		return super.cacheResolver();
	}

	@Override
	public CacheErrorHandler errorHandler() {

		return super.errorHandler();
	}

	@Bean
	public KeyGenerator keyGenerator() {

		return new KeyGenerator() {

			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}*/
}
