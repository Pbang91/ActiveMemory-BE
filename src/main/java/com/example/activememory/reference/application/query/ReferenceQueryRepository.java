package com.example.activememory.reference.application.query;

import com.example.activememory.reference.application.query.model.BodyPartReadModel;
import com.example.activememory.reference.application.query.model.ExerciseTypeReadModel;
import com.example.activememory.reference.application.query.model.MuscleReadModel;
import com.example.activememory.reference.application.query.model.StandardExerciseReadModel;
import com.example.activememory.reference.domain.entity.*;
import com.example.activememory.reference.domain.enums.MuscleRole;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ReferenceQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QBodyPart bodyPart = QBodyPart.bodyPart;
    private final QStandardExercise standardExercise = QStandardExercise.standardExercise;
    private final QStandardExerciseMuscle exerciseMuscle = QStandardExerciseMuscle.standardExerciseMuscle;
    private final QMuscle muscle = QMuscle.muscle;

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

    public List<StandardExerciseReadModel> findStandardExercises() {
        Map<String, String> bodyPartMap = queryFactory
                .selectFrom(bodyPart)
                .fetch()
                .stream()
                .collect(Collectors.toMap(bp -> bp.getBodyPartCode().value(), BodyPart::getName));

        List<StandardExercise> exercises = queryFactory
                .selectFrom(standardExercise)
                .leftJoin(standardExercise.exerciseMuscles, exerciseMuscle).fetchJoin()
                .leftJoin(exerciseMuscle.muscle, muscle).fetchJoin()
                .distinct()
                .fetch();

        if (exercises.isEmpty()) {
            return List.of();
        }

        return exercises
                .stream()
                .map(ex -> {
                    String code = ex.getBodyPartCode().value();
                    String bodyPartName = bodyPartMap.get(code);
                    return new StandardExerciseReadModel(
                            ex.getStandardExerciseId().value(),
                            ex.getName(),
                            ex.getDescription(),
                            new BodyPartReadModel(code, bodyPartName),
                            new ExerciseTypeReadModel(
                                    ex.getType().name(),
                                    ex.getType().getKoName()
                            ),
                            ex.getExerciseMuscles()
                                    .stream()
                                    .map(map -> new MuscleReadModel(
                                            map.getMuscle().getMuscleId().value(),
                                            map.getMuscle().getName(),
                                            map.getRole()
                                    ))
                                    .sorted(Comparator
                                            .<MuscleReadModel, Boolean>comparing(m -> MuscleRole.PRIMARY.equals(m.role()))
                                            .thenComparing(MuscleReadModel::role)
                                            .reversed()
                                    )
                                    .toList()
                    );
                })
                .sorted(Comparator.comparing(StandardExerciseReadModel::id))
                .toList();
    }
}
