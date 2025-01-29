package com.m51.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;//manages the connection to the Redis data store.
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        RedisTemplate<String,Object> template=new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);//links the Redis template with the Redis connection factory.
        template.setKeySerializer(new StringRedisSerializer());//ensures that keys are stored as readable strings in Redis.
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());//enabling JSON serialization and deserialization for storing complex objects.
        return template;
    }
}
