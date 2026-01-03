package com.example.activememory.account.auth.application.command.dto;

import com.example.activememory.global.enums.AuthType;

public record LoginCommand(
        AuthType type,
        String email,
        String password,
        String token
) {
}
