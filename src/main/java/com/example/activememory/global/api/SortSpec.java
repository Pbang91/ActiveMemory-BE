package com.example.activememory.global.api;

import io.swagger.v3.oas.annotations.media.Schema;

public record SortSpec(
        @Schema(description = "정렬 대상", example = "createdAt", requiredMode = Schema.RequiredMode.REQUIRED)
        String property,

        @Schema(description = "정렬 기준", example = "desc", requiredMode = Schema.RequiredMode.REQUIRED)
        String direction
) {
}
