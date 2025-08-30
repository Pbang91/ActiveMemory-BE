package com.example.activememory.user.domain.user.entity;

import com.example.activememory.global.share.id.UserId;
import com.example.activememory.global.share.converter.UserIdConverter;
import com.example.activememory.user.domain.user.enums.OAuthType;
import com.example.activememory.user.domain.user.vo.OAuthInfo;
import com.example.activememory.user.domain.user.vo.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_users_oauth",
                        columnNames = {"oauth_type", "oauth_id"}
                )
        }
)
public class User {
    @Id
    @Convert(converter = UserIdConverter.class)
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "uuid")
    private UserId id;

    @Getter
    @Embedded
    private OAuthInfo oauthInfo;

    @Getter
    @Embedded
    private Profile profile;

    @Getter
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    protected User() {}

    private User(OAuthInfo oauthInfo, Profile profile) {
        this.oauthInfo = oauthInfo;
        this.profile = profile;
    }

    /**
     * User Instance를 생성하는 함수
     *
     * @param oauthId OAuth Provider에서 발급하는 유저 식별자
     * @param oauthType OAuth Provider type
     * @param oauthAccessToken OAuth Provider에서 로그인을 진행 시 발급하는 token
     * @param nickname 서비스에서 표현될 닉네임
     * @param imageUrl [optional] 사용자 프로필 url
     * @param bio [optional] 사용자 소개
     * @return User
     */
    public static User of(
            String oauthId,
            OAuthType oauthType,
            String oauthAccessToken,
            String nickname,
            String imageUrl,
            String bio
    ) {
        OAuthInfo oi = OAuthInfo.of(oauthId, oauthType, oauthAccessToken);
        Profile pf = Profile.of(nickname, imageUrl, bio);

        User user = new User(oi, pf);
        user.id = UserId.of(UUID.randomUUID());
        return user;
    }

    // 변경 메서드(불변 덮어쓰기)
    public void changeProfile(String nickname, String imageUrl, String bio) {
        this.profile = Profile.of(nickname, imageUrl, bio);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null)  return false;
        if (!(o instanceof User other)) return false;

        return other.id.equals(id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
