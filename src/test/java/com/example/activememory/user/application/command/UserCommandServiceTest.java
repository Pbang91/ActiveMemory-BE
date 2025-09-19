package com.example.activememory.user.application.command;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserCommandService 테스트")
public class UserCommandServiceTest {
    @InjectMocks
    private UserCommandService userCommandService;

    @Mock
    private KakaoAdapter kakaoAdapter;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RedisService redis;

    @Mock
    private JwtService jwtService;

    @Nested
    @DisplayName("OAuth 토큰 인증 테스트")
    class AuthWithOAuthToken {
        private final String oauthAccessToken = "test-access-token";
        private final String oauthRefreshToken = "test-refresh-token";
        private final String oauthId = "kakao-id-123";
        private GetUserInformationResDto userInfo;

        @BeforeEach
        void setUp() {
            userInfo = new GetUserInformationResDto(oauthId);
        }

        @Test
        @DisplayName("성공 - 기존 사용자가 카카오로 로그인 시 임시 코드 반환")
        void givenExistingUser_whenAuthWithOAuth_thenReturnsTempCode() {
            UserId existingUseId = UserId.of(UUID.randomUUID());
            User existingUser = mock(User.class);
            OAuthInfo oauthInfo = OAuthInfo.of(oauthId, OAuthType.KAKAO, oauthAccessToken);

            given(kakaoAdapter.getUserInformationByToken(oauthAccessToken)).willReturn(userInfo);
            given(userRepository.findByOAuthInfo(oauthInfo)).willReturn(Optional.of(existingUser));
            given(existingUser.getId()).willReturn(existingUseId);

            String tempCode = userCommandService.authWithOAuthToken(OAuthType.KAKAO, oauthAccessToken, oauthRefreshToken);

            assertThat(tempCode).isNotNull().isNotEmpty();
            verify(kakaoAdapter, times(1)).getUserInformationByToken(oauthAccessToken);
            verify(userRepository, times(1)).findByOAuthInfo(oauthInfo);
            verify(userRepository, never()).save(any(User.class));
        }

        @Test
        @DisplayName("성공 - 신규 사용자가 카카오로 로그인 시 유저를 생성하고 임시 코드를 반환")
        void givenNewUser_whenAuthWithOAuth_thenCreatesUserAndReturnsTempCode() {
            UserId newUserId = UserId.of(UUID.randomUUID());
            User newUser = mock(User.class);
            OAuthInfo oauthInfo = OAuthInfo.of(oauthId, OAuthType.KAKAO, oauthAccessToken);

            given(kakaoAdapter.getUserInformationByToken(oauthAccessToken)).willReturn(userInfo);
            given(userRepository.findByOAuthInfo(oauthInfo)).willReturn(Optional.empty());
            given(userRepository.save(any(User.class))).willReturn(newUser);
            given(newUser.getId()).willReturn(newUserId);

            String tempCode = userCommandService.authWithOAuthToken(OAuthType.KAKAO, oauthAccessToken, oauthRefreshToken);

            assertThat(tempCode).isNotNull().isNotEmpty();
            verify(kakaoAdapter, times(1)).getUserInformationByToken(oauthAccessToken);
            verify(userRepository, times(1)).findByOAuthInfo(oauthInfo);
            verify(userRepository, times(1)).save(any(User.class));
        }

        @Test
        @DisplayName("실패 - 지원하지 않는 OAuth 타입으로 로그인 시 예외 발생")
        void givenUnsupportedType_whenAuthWithOAuth_thenThrowsException() {
            OAuthType unsupportedType = OAuthType.APPLE;

            CustomException exception = assertThrows(CustomException.class, () -> userCommandService.authWithOAuthToken(unsupportedType, oauthAccessToken, oauthRefreshToken));

            assertThat(exception.getExceptionCode()).isEqualTo(ExceptionCode.INVALID_PARAMETER);
            verify(kakaoAdapter, never()).getUserInformationByToken(oauthAccessToken);
            verify(userRepository, never()).findByOAuthInfo(any(OAuthInfo.class));
        }
    }

    @Nested
    @DisplayName("임시 코드로 유저 인가 테스트")
    class AuthorizeUserWithCodeTest {

        private final String code = UUID.randomUUID().toString();
        private final String key = PrefixCode.AUTH_TEMP_CODE + ":" + code;
        private final UserId userId = UserId.of(UUID.randomUUID());

        @Test
        @DisplayName("성공 - 유효한 임시 코드로 요청 시 토큰(액세스, 리프레시)과 device Id 발급")
        void givenValidCode_whenAuthorize_thenReturnsAuthTokens() {
            String expectedAccessToken = "generated-access-token";
            String expectedRefreshToken = "generated-refresh-token";

            given(redis.get(key, UserId.class)).willReturn(userId);
            given(jwtService.generateAccessToken(eq(userId.value()), anyString(), isNull())).willReturn(expectedAccessToken);
            given(jwtService.generateRefreshToken(eq(userId.value()), anyString(), isNull())).willReturn(expectedRefreshToken);

            AuthTokenResDto result = userCommandService.authorizeUserWithCode(code);

            assertThat(result).isNotNull();
            assertThat(result.accessToken()).isEqualTo(expectedAccessToken);
            assertThat(result.refreshToken()).isEqualTo(expectedRefreshToken);
            assertThat(result.deviceId()).isNotNull().isNotEmpty();

            verify(redis, times(1)).get(key, UserId.class);
            verify(jwtService, times(1)).generateAccessToken(eq(userId.value()), anyString(), isNull());
            verify(jwtService, times(1)).generateRefreshToken(eq(userId.value()), anyString(), isNull());
            verify(redis, times(1)).delete(key); // finally 블록에서 항상 호출되어야 함
        }

        @Test
        @DisplayName("실패 - 유효하지 않거나 만료된 코드로 요청 시 예외 발생")
        void givenInvalidCode_whenAuthorize_thenThrowsException() {
            given(redis.get(key, UserId.class)).willReturn(null); // Redis에 해당 키가 없음

            CustomException exception = assertThrows(CustomException.class, () ->
                    userCommandService.authorizeUserWithCode(code)
            );

            assertThat(exception.getExceptionCode()).isEqualTo(ExceptionCode.FORBIDDEN_USER);
            verify(redis, times(1)).get(key, UserId.class);
            verify(jwtService, never()).generateAccessToken(any(), any(), any());
            verify(redis, times(1)).delete(key);
        }
    }
}
