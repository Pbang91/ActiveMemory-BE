package com.example.activememory.account.auth.application.command;

import com.example.activememory.account.auth.application.command.dto.LoginCommand;
import com.example.activememory.account.auth.application.command.dto.TokenResponse;
import com.example.activememory.account.auth.application.port.AuthStrategy;
import com.example.activememory.account.auth.domain.AuthRegistry;
import com.example.activememory.account.auth.domain.entity.AuthSession;
import com.example.activememory.account.auth.domain.entity.AuthTargetUser;
import com.example.activememory.global.exception.BusinessException;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    private final List<AuthStrategy> strategies;
    private final AuthRegistry authRegistry;
    private final JwtService jwtService;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpiration;

    public AuthService(List<AuthStrategy> strategies, AuthRegistry authRegistry, JwtService jwtService) {
        this.strategies = strategies;
        this.authRegistry = authRegistry;
        this.jwtService = jwtService;
    }

    public TokenResponse login(LoginCommand command) {
        /*
        1. 전략 찾기 및 인증 위임(OCP)
        2. 세션 생성 및 토큰 발급
         */
        AuthStrategy strategy = strategies
                .stream()
                .filter(s -> s.supports(command.type()))
                .findFirst()
                .orElseThrow(() -> new BusinessException(ExceptionCode.INVALID_AUTH_TYPE));

        AuthTargetUser user = strategy.authenticate(command);

        String newDeviceId = UUID.randomUUID().toString();

        String accessToken = jwtService.generateAccessToken(user.userId(), newDeviceId, null);
        String refreshToken = jwtService.generateRefreshToken(user.userId(), newDeviceId, null);

        AuthSession authSession = new AuthSession(user.userId(), newDeviceId, refreshToken, refreshTokenExpiration);

        authRegistry.save(authSession);

        return new TokenResponse(accessToken, refreshToken);
    }
}
