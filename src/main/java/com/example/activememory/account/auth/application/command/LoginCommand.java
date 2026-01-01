package com.example.activememory.account.auth.application.command;

public record LoginCommand(
        String type,
        String email,
        String password
) {
}
