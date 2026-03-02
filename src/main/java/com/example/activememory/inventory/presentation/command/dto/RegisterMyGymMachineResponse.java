package com.example.activememory.inventory.presentation.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RegisterMyGymMachineResponse(
        @NotNull
        @Schema(description = "생성(혹은 기존의)된 CustomMachine Id", example = "2")
        Long machineId
) {
}
