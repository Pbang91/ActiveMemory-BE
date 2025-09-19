package com.example.activememory.user.domain.user.vo;

import com.example.activememory.user.domain.user.enums.OAuthType;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Embeddable
public class OAuthInfo {
    @Getter
    @Column(name = "oauth_id", nullable = false, unique = true)
    private String id;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "oauth_type", nullable = false)
    private OAuthType type;

    @Column(name = "oauth_access_token", nullable = false)
    private String accessToken;

    protected OAuthInfo() {}

    private OAuthInfo(String oauthId, OAuthType oauthType, String oauthAccessToken) {
        this.id = oauthId;
        this.type = oauthType;
        this.accessToken = oauthAccessToken;
    }

    public static OAuthInfo of(String oauthId, OAuthType oauthType, String oauthAccessToken) {
        return new OAuthInfo(oauthId, oauthType, oauthAccessToken);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)  return false;
        if (!(o instanceof OAuthInfo other)) return false;

        return Objects.equals(id, other.id)
                && Objects.equals(type, other.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
