package com.example.activememory.account.auth.domain.entity;

public record AuthTargetUser(
        Long userId,
        String email,
        String password
) {
}
