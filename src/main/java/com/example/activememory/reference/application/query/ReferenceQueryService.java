package com.example.activememory.reference.application.query;

import com.example.activememory.reference.application.query.model.BodyPartReadModel;
import com.example.activememory.reference.application.query.model.StandardExerciseReadModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceQueryService {
    private final ReferenceQueryRepository referenceRepo;

    public ReferenceQueryService(ReferenceQueryRepository referenceRepo) {
        this.referenceRepo = referenceRepo;
    }

    public List<BodyPartReadModel> findBodyParts() {
        return referenceRepo.findBodyParts().orElse(List.of());
    }

    public List<StandardExerciseReadModel> findStandardExercises() {
        return referenceRepo.findStandardExercises().orElse(List.of());
    }
}
