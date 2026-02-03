package com.example.activememory.reference.domain.gym.service.dto;

public record PlaceSearchResponse(
        String providerId,
        String name,
        String address,
        String longitude,
        String latitude
) {
}
