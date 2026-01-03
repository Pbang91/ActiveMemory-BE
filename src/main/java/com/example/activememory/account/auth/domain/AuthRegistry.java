package com.example.activememory.account.auth.domain;


import com.example.activememory.account.auth.domain.entity.AuthSession;

public interface AuthRegistry {
    String getActiveDeviceId(Long userId);

    void save(AuthSession authSession);
}
