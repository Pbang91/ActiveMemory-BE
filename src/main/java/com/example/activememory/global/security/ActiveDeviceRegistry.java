package com.example.activememory.global.security;

import java.util.UUID;

public interface ActiveDeviceRegistry {
    String getActiveDeviceId(UUID userId);
    void setActiveDeviceId(UUID userId, String deviceId); // 로그인 성공 시
    void revokeAllForUser(UUID userId); // 강제 로그아웃 사용 시
}
