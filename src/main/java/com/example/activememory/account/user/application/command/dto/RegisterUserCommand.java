package com.example.activememory.account.user.application.command.dto;

public record RegisterUserCommand(
        String email,
        String password,
        String nickname,
        String bio
) {
}
