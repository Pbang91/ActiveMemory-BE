package com.example.activememory.global.security;

import com.example.activememory.global.enums.PrefixCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisActiveDeviceRegistry implements ActiveDeviceRegistry {
    @Value("${jwt.refresh.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;
    private final StringRedisTemplate redis;

    public RedisActiveDeviceRegistry(StringRedisTemplate stringRedisTemplate) {
        this.redis = stringRedisTemplate;
    }

    private String key(UUID userId) {
        return PrefixCode.ACTIVE_DEVICE + ":" + userId;
    }

    @Override
    public String getActiveDeviceId(UUID userId) {
        return redis.opsForValue().get(key(userId));
    }

    @Override
    public void setActiveDeviceId(UUID userId, String deviceId) {
        redis.opsForValue().set(key(userId), deviceId, REFRESH_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
    }

    @Override
    public void revokeAllForUser(UUID userId) {
        redis.delete(key(userId));
    }
}
