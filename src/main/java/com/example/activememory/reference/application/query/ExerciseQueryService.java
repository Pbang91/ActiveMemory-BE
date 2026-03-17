package com.example.activememory.reference.application.query;

import com.example.activememory.global.enums.ExerciseType;
import com.example.activememory.reference.application.query.model.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ExerciseQueryService {
    private final ExerciseQueryRepository referenceRepo;

    public ExerciseQueryService(ExerciseQueryRepository referenceRepo) {
        this.referenceRepo = referenceRepo;
    }

    public List<BodyPartReadModel> findBodyParts() {
        return referenceRepo.findBodyParts().orElse(List.of());
    }

    public List<ExerciseTypeReadModel> getExerciseType() {
        return Arrays
                .stream(ExerciseType.values())
                .map(type -> new ExerciseTypeReadModel(type.name(), type.getKoName()))
                .toList();
    }

    public List<StandardExerciseReadModel> findStandardExercises() {
        return referenceRepo.findStandardExercises();
    }

    public List<MuscleReadModel> findStandardMuscles() {
        return referenceRepo.findMuscles();
    }
}
