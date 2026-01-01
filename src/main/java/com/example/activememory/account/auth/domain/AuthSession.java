package com.example.activememory.account.auth.domain;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Getter
@RedisHash("auth:session")
public class AuthSession {
    @Id
    private Long userId;

    private String deviceId;

    private String refreshToken;

    @TimeToLive(unit = TimeUnit.MILLISECONDS)
    private Long expiration;

    public AuthSession(Long userId, String deviceId, String refreshToken, Long expiration) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }

    public void rotateToken(String newRefreshToken, Long newExpiration) {
        this.refreshToken = newRefreshToken;
        this.expiration = newExpiration;
    }
}
