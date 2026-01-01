package com.example.activememory.account.auth.domain;


public interface AuthRegistry {
    String getActiveDeviceId(Long userId);

    void save(AuthSession authSession);
}
