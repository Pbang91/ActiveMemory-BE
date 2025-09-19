package com.example.activememory.user.application.command.port.dto;

import jakarta.validation.constraints.NotBlank;

public record GetOAuthTokenReqDto(
        @NotBlank
        String code,
        String idToken
) {
}
