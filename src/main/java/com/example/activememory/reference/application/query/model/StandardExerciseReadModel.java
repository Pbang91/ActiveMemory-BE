package com.example.activememory.reference.application.query.model;

import com.example.activememory.reference.domain.vo.BodyPartCode;

public record StandardExerciseReadModel(
        Long id,
        String bodyPartCode,
        String name,
        String type,
        String description
) {
    public StandardExerciseReadModel(Long id, BodyPartCode bodyPartCode, String name, String type, String description) {
        this(
                id,
                bodyPartCode != null ? bodyPartCode.value() : null, // VO -> String 변환
                name,
                type,
                description
        );
    }
}
