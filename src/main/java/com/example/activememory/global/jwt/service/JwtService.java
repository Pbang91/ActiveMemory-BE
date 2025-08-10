package com.example.activememory.global.jwt.service;

import com.example.activememory.global.security.ActiveDeviceRegistry;
import com.example.activememory.global.security.CustomUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

@Service
public class JwtService {
    @Value("${jwt.secret}") private String SECRET;
    @Value("${jwt.access.expiration}") private Long ACCESS_TOKEN_EXPIRATION;
    @Value("${jwt.refresh.expiration}")  private Long REFRESH_TOKEN_EXPIRATION;
    private final ActiveDeviceRegistry activeDeviceRegistry;

    public JwtService(ActiveDeviceRegistry activeDeviceRegistry) {
        this.activeDeviceRegistry = activeDeviceRegistry;
    }

    /**
     * JWT SignKey 생성
     *
     * @return key
     */
    private SecretKey key() {
        byte [] keyByte = SECRET.getBytes(StandardCharsets.UTF_8);


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
    public Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload();
    }

    /**
     * JWT에서 subject(userId - string) 파싱
     *
     * @param token access or refresh token
     * @return userId
     */
    public String extractSubject(String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * JWT 유효 시간 검증
     *
     * @param token access or refresh token
     * @return 검증값
     */
    public boolean isExpired(String token) {
        Date exp =  extractAllClaims(token).getExpiration();
        return exp == null || exp.before(new Date());
    }

    /**
     * AccessToken 맞는지 확인
     *
     * @param token 전달된 토큰
     * @return 검증값
     */
    public boolean isAccessToken(String token) {
        Object typ = extractAllClaims(token).get("typ");
        return "access".equals(typ);
    }

    /**
     * RefreshToken 맞는지 확인
     *
     * @param token 전달된 토큰
     * @return 검증값
     */
    public boolean isRefreshToken(String token) {
        Object typ = extractAllClaims(token).get("typ");
        return "refresh".equals(typ);
    }

    /**
     * AccessToken 생성
     *
     * @param userId 현재 요청하는 userId
     * @param deviceId 현재 요청하는 user의 deviceId
     * @param extraClaims 그 외 claims
     * @return accessToken
     */
    public String generateAccessToken(UUID userId, String deviceId, Map<String, Object> extraClaims) {
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
     * @param userId 현재 요청하는 userId
     * @param deviceId 현재 요청하는 user의 deviceId
     * @param extraClaims 그 외 claims
     * @return refreshToken
     */
    public String generateRefreshToken(UUID userId, String deviceId, Map<String, Object> extraClaims) {
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
            if (isExpired(accessToken) || !isAccessToken(accessToken)) {
                return null;
            }

            Claims claims = extractAllClaims(accessToken);
            UUID userId = UUID.fromString(claims.getSubject());
            String deviceId = claims.get("deviceId", String.class);

            // 활성 디바이스 확인(계정당 1개)
            String activeDeviceId = activeDeviceRegistry.getActiveDeviceId(userId);

            if (activeDeviceId == null || !Objects.equals(activeDeviceId, deviceId)) {
                return null;
            }

            UserDetails user = new CustomUserDetail(userId, null, deviceId, null);

            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        } catch (Exception e) {
            return null;
        }
    }
}
