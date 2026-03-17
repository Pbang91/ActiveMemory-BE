package com.example.activememory.inventory.presentation.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RegisterMyGymResponse(
        @NotNull
        @Schema(description = "생성(혹은 기존의)된 MyGym Id", example = "2")
        Long myGymId
) {
}
