package com.example.activememory.account.auth.domain;

import com.example.activememory.account.user.domain.vo.UserId;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AuthPolicy {
    private final AuthRegistry authRegistry;

    public AuthPolicy(AuthRegistry authRegistry) {
        this.authRegistry = authRegistry;
    }

    public boolean isValidDevice(Long userId, String tokenDeviceId) {
        String activeDeviceId = authRegistry.getActiveDeviceId(userId);
    }
}
