package com.example.activememory.user.infrastructure.acl.kakao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoAccountResDto {
    @Schema(description = "사용자 동의 시 프로필 정보 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean profile_needs_agreement;

    @Schema(description = "사용자 동의 시 닉네임 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean profile_nickname_needs_agreement;

    @Schema(description = "사용자 동의 시 프로필 사진 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean profile_image_needs_agreement;

    @Schema(description = "프로필 정보", requiredMode = Schema.RequiredMode.NOT_REQUIRED, implementation = KakaoProfileResDto.class)
    private KakaoProfileResDto profile;

    @Schema(description = "사용자 동의 시 카카오계정 이름 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean name_needs_agreement;

    @Schema(description = "카카오계정 이름", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String name;

    @Schema(description = "사용자 동의 시 카카오계정 대표 이메일 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean email_needs_agreement;

    @Schema(description = "이메일 유효 여부", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean is_email_valid;

    @Schema(description = "이메일 인증 여부", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean is_email_verified;

    @Schema(description = "카카오계정 대표 이메일", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String email;

    @Schema(description = "사용자 동의 시 연령대 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean age_range_needs_agreement;

    @Schema(description = "연령대", example = "1~9",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String age_range;

    @Schema(description = "사용자 동의 시 출생 연도 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean birthyear_needs_agreement;

    @Schema(description = "출생 연도(YYYY 형식)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String birthyear;

    @Schema(description = "사용자 동의 시 생일 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean birthday_needs_agreement;

    @Schema(description = "생일(MMDD 형식)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String birthday;

    @Schema(description = "생일 타입. SOLAR or LUNAR", example = "SOLAR", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String birthday_type;

    @Schema(description = "생일의 윤달 여부", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean is_leap_month;

    @Schema(description = "사용자 동의 시 성별 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean gender_needs_agreement;

    @Schema(description = "성별. female or male", example = "female", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String gender;

    @Schema(description = "사용자 동의 시 전화번호 제공 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean phone_number_needs_agreement;

    @Schema(
            description = "카카오 계정의 전화번호\n\n" +
                    "국내 번호인 경우 +82 00-0000-0000 형식\n\n" +
                    "해외 번호인 경우 자릿수, 붙임표(-)유무나 위치가 다를 수 있음",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String phone_number;

    @Schema(description = "사용자 동의 시 CI 참고 가능", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Boolean ci_needs_agreement;

    @Schema(description = "연계 정보", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String ci;

    @Schema(description = "CI 발급 시각. UTC", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String ci_authenticated_at;
}
