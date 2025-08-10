package com.example.activememory.user.application.query.dto.response;

import com.example.activememory.user.domain.user.enums.OAuthType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record OAuthInfoResDto(
        @Schema(description = "회원가입 시 이용한 OAuth Provider", example = "KAKAO", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull OAuthType type
) {
    public OAuthInfoResDto(OAuthType type) {
        if (type == null) {
            // TODO: 뭔가 문제 생김
        }

        this.type = type;
    }
}
