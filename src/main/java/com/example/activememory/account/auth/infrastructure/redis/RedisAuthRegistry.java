package com.example.activememory.account.auth.infrastructure.redis;

import com.example.activememory.account.auth.domain.AuthRegistry;
import com.example.activememory.account.auth.domain.entity.AuthSession;
import com.example.activememory.account.auth.domain.repository.AuthSessionRepository;
import org.springframework.stereotype.Component;

@Component
public class RedisAuthRegistry implements AuthRegistry {
    private final AuthSessionRepository repository;

    public RedisAuthRegistry(AuthSessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getDeviceIdById(Long userId) {
        return repository.findById(userId).map(AuthSession::getDeviceId).orElse(null);
    }

    @Override
    public AuthSession findById(Long userId) {
        return repository.findById(userId).orElse(null);
    }

    @Override
    public void save(AuthSession authSession) {
        repository.save(authSession);
    }

    @Override
    public void deleteById(Long userId) {
        repository.deleteById(userId);
    }
}
