package com.example.activememory.global.redis;

import com.example.activememory.global.exception.CustomException;
import com.example.activememory.global.exception.ExceptionCode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("unchecked")
    private <T> T convert(Object value, Class<T> clazz) {
        if (clazz == Long.class && value instanceof Integer intVal) {
            return (T) Long.valueOf(intVal);
        }

        if (clazz == Double.class && value instanceof Integer intVal) {
            return (T) Double.valueOf(intVal);
        }

        if (clazz == Boolean.class && value instanceof Boolean boolVal) {
            return (T) boolVal;
        }

        if (clazz == String.class && value instanceof String stringVal) {
            return (T) stringVal;
        }

        return clazz.cast(value);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    public <T> T get(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForValue().get(key);

        if (value == null) return null;

        try {
            return convert(value, clazz);
        } catch (ClassCastException e) {
            throw new CustomException(ExceptionCode.INTERNAL_SERVER_ERROR, "레디스 캐스팅 오류." + clazz.getSimpleName() + " 사유: " + e.getMessage());
        }
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
