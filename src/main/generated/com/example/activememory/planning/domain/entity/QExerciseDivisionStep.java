package com.example.activememory.planning.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseDivisionStep is a Querydsl query type for ExerciseDivisionStep
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseDivisionStep extends EntityPathBase<ExerciseDivisionStep> {

    private static final long serialVersionUID = 175198541L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseDivisionStep exerciseDivisionStep = new QExerciseDivisionStep("exerciseDivisionStep");

    public final QExerciseDivision exerciseDivision;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> performableId = createNumber("performableId", Long.class);

    public final EnumPath<com.example.activememory.global.enums.PerformableType> performableType = createEnum("performableType", com.example.activememory.global.enums.PerformableType.class);

    public final NumberPath<Integer> stepOrder = createNumber("stepOrder", Integer.class);

    public final NumberPath<Integer> targetReps = createNumber("targetReps", Integer.class);

    public final NumberPath<Integer> targetSets = createNumber("targetSets", Integer.class);

    public final NumberPath<java.math.BigDecimal> targetWeight = createNumber("targetWeight", java.math.BigDecimal.class);

    public QExerciseDivisionStep(String variable) {
        this(ExerciseDivisionStep.class, forVariable(variable), INITS);
    }

    public QExerciseDivisionStep(Path<? extends ExerciseDivisionStep> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseDivisionStep(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseDivisionStep(PathMetadata metadata, PathInits inits) {
        this(ExerciseDivisionStep.class, metadata, inits);
    }

    public QExerciseDivisionStep(Class<? extends ExerciseDivisionStep> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.exerciseDivision = inits.isInitialized("exerciseDivision") ? new QExerciseDivision(forProperty("exerciseDivision"), inits.get("exerciseDivision")) : null;
    }

}

