package com.example.activememory.reference.application.query.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record BodyPartReadModel(
        @NotNull
        @Schema(description = "대분류 CODE", example = "CHEST")
        String code,

        @NotNull
        @Schema(description = "대분류 한글 이름", example = "가슴")
        String name
) {
}
