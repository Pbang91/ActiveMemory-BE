package com.example.activememory.user.infrastructure.acl.kakao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Getter
public class GetKakaoUserInformationResDto {
    @NotNull
    @Schema(description = "회원번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(
            description = "자동 연결 설정을 비활성화한 경우만 존재\n\n" +
                    "연결하기 호출의 완료 여부\n\n" +
                    "false: 연결대기 상태 / true: 연결 상태",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private Boolean has_signed_up;

    @Schema(description = "서비스에 연결 완료된 시각. UTC", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime connected_at;

    @Schema(description = "카카오싱크 간편가입으로 로그인한 시각. UTC", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime synched_at;

    @Schema(description = "사용자 프로퍼티", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Map<String, Object> properties;

    @Schema(description = "카카오계정 정보", requiredMode = Schema.RequiredMode.NOT_REQUIRED, implementation = KakaoAccountResDto.class)
    private KakaoAccountResDto kakao_account;

    @Schema(description = "uuid 등 추가 정보", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Object for_partner;
}
