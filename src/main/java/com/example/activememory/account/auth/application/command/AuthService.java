package com.example.activememory.account.auth.application.command;

import com.example.activememory.account.auth.application.port.UserPort;
import com.example.activememory.account.auth.domain.AuthRegistry;
import com.example.activememory.account.auth.domain.AuthSession;
import com.example.activememory.account.auth.domain.AuthTargetUser;
import com.example.activememory.global.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    private final AuthRegistry authRegistry;
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpiration;

    public AuthService(AuthRegistry authRegistry, UserPort userPort, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authRegistry = authRegistry;
        this.userPort = userPort;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void login(LoginCommand command) {
        /*
        1. 사용자 검증
        2. UserId 기반 DeviceId 생성
        3. 토큰 발급 - newDeviceId
        4. Redis 저장 - 덮어쓰기
         */
        AuthTargetUser user = userPort.loadUserByEmail(command.email()).orElseThrow(() -> new RuntimeException("이런 이런"));

        if (!passwordEncoder.matches(command.password(), user.password())) {
            throw new IllegalArgumentException("이런 이런");
        }

        String newDeviceId = UUID.randomUUID().toString();
        String accessToken = jwtService.generateAccessToken(user.userId(), newDeviceId, null);
        String refreshToken = jwtService.generateRefreshToken(user.userId(), newDeviceId, null);

        AuthSession authSession = new AuthSession(user.userId(), newDeviceId, refreshToken, refreshTokenExpiration);

        authRegistry.save(authSession);
    }
}
