package com.dc.boot_redis02.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-10
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    /**
     * 保证不是序列化后的乱码配置
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
