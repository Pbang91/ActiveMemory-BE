package com.example.activememory.global.api;

import io.swagger.v3.oas.annotations.media.Schema;

public record ExceptionResDto(
        @Schema(description = "에러 코드", example = "0001")
        String code,

        @Schema(description = "에러 설명", example = "해당 샵을 찾을 수 없습니다")
        String description,

        @Schema(description = "에러 추가 설명", example = "ID가 존재하지 않습니다")
        String details
) {
}
