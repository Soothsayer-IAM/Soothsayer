package com.soothsayer.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

@Configuration
public class RedisConfiguration {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

    @Bean
    public RedisManager redisManager(RedisTemplate<String, Object> redisTemplate) {
        return new RedisManager(redisTemplate);
    }

    public class RedisManager {

        private RedisTemplate<String, Object> redisTemplate;

        public RedisManager(RedisTemplate<String, Object> redisTemplate) {
            this.redisTemplate = redisTemplate;
        }

        public void setValue(String k, Object v, long time, TimeUnit timeUnit) {
            redisTemplate.opsForValue().set(k, v, time, timeUnit);
        }

        public Object getValue(String k) {
            return redisTemplate.opsForValue().get(k);
        }
        public void removeValue(String k) {
            redisTemplate.delete(k);
        }
    }

}
