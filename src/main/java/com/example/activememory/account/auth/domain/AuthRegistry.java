package com.example.activememory.account.auth.domain;


import com.example.activememory.account.auth.domain.entity.AuthSession;

public interface AuthRegistry {
    String getDeviceIdById(Long userId);
    
    AuthSession findById(Long userId);

    void save(AuthSession authSession);

    void deleteById(Long userId);
}
