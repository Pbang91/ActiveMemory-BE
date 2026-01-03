package com.example.activememory.reference.application.query;

import com.example.activememory.reference.application.query.model.BodyPartReadModel;
import com.example.activememory.reference.application.query.model.StandardExerciseReadModel;
import com.example.activememory.reference.domain.entity.QBodyPart;
import com.example.activememory.reference.domain.entity.QStandardExercise;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReferenceQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QBodyPart bodyPart = QBodyPart.bodyPart;
    private final QStandardExercise standardExercise = QStandardExercise.standardExercise;

    public Optional<List<BodyPartReadModel>> findBodyParts() {
        return Optional.ofNullable(
                queryFactory.select(
                                Projections.constructor(
                                        BodyPartReadModel.class,
                                        bodyPart.code,
                                        bodyPart.name
                                ))
                        .from(bodyPart)
                        .fetch()
        );
    }

    public Optional<List<StandardExerciseReadModel>> findStandardExercises() {
        return Optional.ofNullable(
                queryFactory.select(
                                Projections.constructor(
                                        StandardExerciseReadModel.class,
                                        standardExercise.id,
                                        standardExercise.bodyPartCode,
                                        standardExercise.name,
                                        standardExercise.type,
                                        standardExercise.description
                                ))
                        .from(standardExercise)
                        .fetch()

        );
    }
}
