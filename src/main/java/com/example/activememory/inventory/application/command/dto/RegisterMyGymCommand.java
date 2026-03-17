package com.example.activememory.inventory.application.command.dto;

import com.example.activememory.account.user.domain.vo.UserId;

public record RegisterMyGymCommand(
        UserId userId,
        String providerId,
        String name,
        String roadAddress,
        String address,
        String phone,
        String x,
        String y
) {
}
