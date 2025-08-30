package com.example.activememory.user.application.query.dto.response;

import com.example.activememory.user.domain.user.vo.OAuthInfo;
import com.example.activememory.user.domain.user.vo.Profile;
import com.example.activememory.user.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class UserResDto {
    @Schema(description = "회원 프로필 정보", implementation = ProfileResDto.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private ProfileResDto profile;

    @Schema(description = "회원 OAuth 정보", implementation = OAuthInfoResDto.class, requiredMode = Schema.RequiredMode.REQUIRED)
    private OAuthInfoResDto oauthInfo;

    @Schema(description = "회원 가입일", example = "2025-05-11", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate createdAt;

    protected UserResDto() {}

    private UserResDto(ProfileResDto profile, OAuthInfoResDto oauthInfo, LocalDateTime createdAt) {
        this.profile = profile;
        this.oauthInfo = oauthInfo;
        this.createdAt = createdAt.toLocalDate();
    }

    public static UserResDto from(User user) {
        Profile profile = user.getProfile();
        OAuthInfo oAuthInfo = user.getOauthInfo();

        return new UserResDto(
                new ProfileResDto(profile.getNickname(), profile.getImageUrl(), profile.getBio()),
                new OAuthInfoResDto(oAuthInfo.getType()),
                user.getCreatedAt()
        );
    }
}