package com.example.activememory.account.auth.domain;

public record AuthTargetUser(
        Long userId,
        String email,
        String password
) {
}
