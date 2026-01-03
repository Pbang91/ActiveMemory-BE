package com.example.activememory.account.auth.application.command.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
