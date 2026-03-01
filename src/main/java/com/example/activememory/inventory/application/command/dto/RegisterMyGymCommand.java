package com.example.activememory.inventory.application.command.dto;

public record RegisterMyGymCommand(
        String providerId,
        String name,
        String roadAddress,
        String address,
        String phone,
        String x,
        String y
) {
}
