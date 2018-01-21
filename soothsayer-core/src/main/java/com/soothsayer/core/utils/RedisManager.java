package com.soothsayer.core.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

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
