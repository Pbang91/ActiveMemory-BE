package com.example.activememory.user.infrastructure.acl.kakao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoProfileResDto {
    @Schema(description = "닉네임", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String nickname;

    @Schema(description = "프로필 미리보기 이미지 URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String thumbnail_image_url;

    @Schema(description = "프로필 사진 URL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String profile_image_url;

    @Schema(
            description = "프로필 사진 URL이 기본 프로필 사진 URL인지 여부\n\n" +
                    "사용자가 등록한 프로필 사진이 없을 경우, 기본 프로필 사진 제공",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Boolean is_default_image;

    @Schema(
            description = "닉네임이 기본 닉네임인지 여부\n\n" +
                    "사용자가 등록한 닉네임이 운영정책에 부합하지 않는 경우,\n\n" +
                    "'닉네임을 등록해주세요'가 기본 닉네임으로 적용됨",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Boolean is_default_nickname;
}
