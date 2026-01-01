package com.example.activememory.account.auth.infrastructure;

import com.example.activememory.account.auth.domain.AuthRegistry;
import com.example.activememory.account.auth.domain.AuthSession;
import com.example.activememory.account.auth.domain.AuthSessionRepository;
import com.example.activememory.global.enums.PrefixCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisAuthRegistry implements AuthRegistry {
    private final AuthSessionRepository repository;

    public RedisAuthRegistry(AuthSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getActiveDeviceId(Long userId) {
        return repository.findById(userId).map(AuthSession::getDeviceId).orElse(null);
    }

    @Override
    public void save(AuthSession authSession) {
        repository.save(authSession);
    }
}
