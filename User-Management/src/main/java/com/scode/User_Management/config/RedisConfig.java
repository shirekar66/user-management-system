package com.scode.User_Management.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig {

    @Bean
    public CommandLineRunner runner(CacheManager cacheManager) {
        return args -> System.out.println("CacheManager = " + cacheManager.getClass().getName());
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration defaultConfig=
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(10))
                        .serializeKeysWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(new StringRedisSerializer()))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(RedisSerializer.json()));

        HashMap<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        cacheConfigurations.put("users",defaultConfig.entryTtl(Duration.ofMinutes(10)));
        cacheConfigurations.put("orders",defaultConfig.entryTtl(Duration.ofMinutes(2)));


        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}
