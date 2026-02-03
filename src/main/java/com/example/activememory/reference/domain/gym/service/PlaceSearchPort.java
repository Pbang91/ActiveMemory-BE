package com.example.activememory.reference.domain.gym.service;

import com.example.activememory.reference.domain.gym.service.dto.PlaceSearchResponse;

import java.util.List;

public interface PlaceSearchPort {
    List<PlaceSearchResponse> searchByKeyword(String keyword);
}
