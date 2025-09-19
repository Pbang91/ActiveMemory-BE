package com.example.activememory.user.application.command;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.example.activememory.global.exception.CustomException;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.jwt.service.JwtService;
import com.example.activememory.global.redis.RedisService;
import com.example.activememory.global.share.enums.PrefixCode;
import com.example.activememory.global.share.id.UserId;
import com.example.activememory.user.application.command.dto.response.AuthTokenResDto;
import com.example.activememory.user.application.command.port.dto.GetUserInformationResDto;
import com.example.activememory.user.domain.user.entity.User;
import com.example.activememory.user.domain.user.enums.OAuthType;
import com.example.activememory.user.domain.user.repository.UserRepository;
import com.example.activememory.user.domain.user.vo.OAuthInfo;
import com.example.activememory.user.infrastructure.acl.kakao.KakaoAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserCommandService {
    private final KakaoAdapter kakaoAdapter;
    private final UserRepository userRepository;
    private final RedisService redis;
    private final JwtService jwtService;

    public UserCommandService(KakaoAdapter kakaoAdapter, UserRepository userRepository, RedisService redis,  JwtService jwtService) {
        this.kakaoAdapter = kakaoAdapter;
        this.userRepository = userRepository;
        this.redis = redis;
        this.jwtService = jwtService;
    }

    private String saveAuthTempCode(UserId userId) {
        String random = UUID.randomUUID().toString();
        String key = PrefixCode.AUTH_TEMP_CODE + ":" + random;

        redis.set(key, userId, Duration.ofSeconds(20));

        return random;
    }

    @Transactional
    public String authWithOAuthToken(
            OAuthType type,
            String oauthAccessToken,
            String oauthRefreshToken
    ) {
        GetUserInformationResDto oauthUserInfo;

        switch (type) {
            case KAKAO -> oauthUserInfo = kakaoAdapter.getUserInformationByToken(oauthAccessToken);
            // TODO: Apple develop 가입 후 진행

//            case APPLE -> throw new CustomException(ExceptionCode.INVALID_PARAMETER, "허용되지 않은 타입입니다");

            default -> throw new CustomException(ExceptionCode.INVALID_PARAMETER, "허용되지 않은 타입입니다");
        }

        assert oauthUserInfo != null;
        OAuthInfo oauthInfo = OAuthInfo.of(oauthUserInfo.oauthId(), type, oauthAccessToken);
        Optional<User> existUser = userRepository.findByOAuthInfo(oauthInfo);

        if (existUser.isPresent()) {
            return saveAuthTempCode(existUser.get().getId());
        } else {
            User user = userRepository.save(
                    User.of(
                            oauthUserInfo.oauthId(),
                            type,
                            oauthAccessToken,
                            NanoIdUtils.randomNanoId(),
                            null,
                            null
                    )
            );

            return saveAuthTempCode(user.getId());
        }
    }

    public AuthTokenResDto authorizeUserWithCode(String code) {
        String key = PrefixCode.AUTH_TEMP_CODE + ":" + code;

        try {
            UserId userId = redis.get(key, UserId.class);

            if (userId == null) {
                throw new CustomException(ExceptionCode.FORBIDDEN_USER);
            }

            String deviceId = UUID.randomUUID().toString();

            String accessToken = jwtService.generateAccessToken(userId.value(), deviceId, null);
            String refreshToken = jwtService.generateRefreshToken(userId.value(), deviceId, null);

            return new AuthTokenResDto(accessToken, refreshToken, deviceId);
        } finally {
            redis.delete(key);
        }
    }
}
