package com.example.activememory.record.presentation.command.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CommentReqDto(
        @Schema(description = "댓글 내용", example = "무게 엄청 치시네요", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotBlank
        String content
) {
}
