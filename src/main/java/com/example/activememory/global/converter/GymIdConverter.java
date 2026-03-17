package com.example.activememory.global.converter;

import com.example.activememory.reference.domain.gym.vo.GymId;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GymIdConverter extends BaseIdConverter<GymId, Long> {
    public GymIdConverter() {
        super(GymId::of);
    }
}
