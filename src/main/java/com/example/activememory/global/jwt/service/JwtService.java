package com.example.activememory.global.jwt.service;

import com.example.activememory.account.auth.domain.AuthRegistry;
import com.example.activememory.global.security.CustomUserDetail;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.access.expiration}")
    private Long ACCESS_TOKEN_EXPIRATION;
    @Value("${jwt.refresh.expiration}")
    private Long REFRESH_TOKEN_EXPIRATION;
    private final AuthRegistry authRegistry;

    public JwtService(AuthRegistry authRegistry) {
        this.authRegistry = authRegistry;
    }

    /**
     * JWT SignKey 생성
     *
     * @return key
     */
    private SecretKey key() {
        byte[] keyByte = SECRET.getBytes(StandardCharsets.UTF_8);


        if (keyByte.length < 32) {
            // TODO: secret 너무 짧음
        }

        return Keys.hmacShaKeyFor(keyByte);
    }

    /**
     * JWT claims 파싱
     *
     * @param token access or refresh token
     * @return token에 들어있는 claims
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
    }

    /**
     * JWT에서 subject(userId - string) 파싱
     *
     * @param token access or refresh token
     * @return userId
     */
    public Long getUseId(String token) {
        return Long.valueOf(extractAllClaims(token).getSubject());
    }


    public boolean validateToken(String token, String type) {
        try {
            Claims claims = extractAllClaims(token);

            return claims.get("typ").equals(type);
        } catch (SecurityException | MalformedJwtException e) {
            // 잘못된 JWT 서명
        } catch (ExpiredJwtException e) {
            // 만료된 토큰
        } catch (UnsupportedJwtException e) {
            // 지원되지 않는 토큰
        } catch (IllegalArgumentException e) {
            // 걍 이상한 토큰
        } catch (Exception e) {
            // 뭔지 모를 에러
        }

        return false;
    }

    /**
     * AccessToken 생성
     *
     * @param userId      현재 요청하는 userId
     * @param deviceId    현재 요청하는 user의 deviceId
     * @param extraClaims 그 외 claims
     * @return accessToken
     */
    public String generateAccessToken(Long userId, String deviceId, Map<String, Object> extraClaims) {
        Instant now = Instant.now();

        Map<String, Object> claims = new HashMap<>();

        if (extraClaims != null) {
            claims.putAll(extraClaims);
        }

        claims.put("deviceId", deviceId);
        claims.put("typ", "access");

        return Jwts.builder()
                .subject(userId.toString())
                .claims(claims)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(ACCESS_TOKEN_EXPIRATION)))
                .id(UUID.randomUUID().toString())
                .signWith(key())
                .compact();
    }

    /**
     * RefreshToken 생성
     *
     * @param userId      현재 요청하는 userId
     * @param deviceId    현재 요청하는 user의 deviceId
     * @param extraClaims 그 외 claims
     * @return refreshToken
     */
    public String generateRefreshToken(Long userId, String deviceId, Map<String, Object> extraClaims) {
        Instant now = Instant.now();

        Map<String, Object> claims = new HashMap<>();

        if (extraClaims != null) {
            claims.putAll(extraClaims);
        }

        claims.put("deviceId", deviceId);
        claims.put("typ", "refresh");

        return Jwts.builder()
                .subject(userId.toString())
                .claims(claims)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(REFRESH_TOKEN_EXPIRATION)))
                .id(UUID.randomUUID().toString())
                .signWith(key())
                .compact();
    }

    public Authentication buildAuthenticationFromAccessToken(String accessToken) {
        try {
            if (!validateToken(accessToken, "access")) {
                return null;
            }

            Claims claims = extractAllClaims(accessToken);
            Long userId = Long.valueOf(claims.getSubject());
            String deviceId = claims.get("deviceId", String.class);

            // 활성 디바이스 확인(계정당 1개)
            String activeDeviceId = authRegistry.getDeviceIdById(userId);

            if (activeDeviceId == null) {
                return null;
            }

            if (!Objects.equals(activeDeviceId, deviceId)) {
                return null;
            }

            UserDetails user = new CustomUserDetail(userId, null, deviceId, null);

            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        } catch (Exception e) {
            return null;
        }
    }
}
