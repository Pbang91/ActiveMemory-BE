package com.example.activememory.account.user.application.query.model;

public record AuthUserReadModel(
        Long userId,
        String email,
        String password
) {
}
