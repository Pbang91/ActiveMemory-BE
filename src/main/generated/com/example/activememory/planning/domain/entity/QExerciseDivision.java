package com.example.activememory.planning.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QExerciseDivision is a Querydsl query type for ExerciseDivision
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QExerciseDivision extends EntityPathBase<ExerciseDivision> {

    private static final long serialVersionUID = 2091790049L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QExerciseDivision exerciseDivision = new QExerciseDivision("exerciseDivision");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QRoutine routine;

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public final ListPath<ExerciseDivisionStep, QExerciseDivisionStep> steps = this.<ExerciseDivisionStep, QExerciseDivisionStep>createList("steps", ExerciseDivisionStep.class, QExerciseDivisionStep.class, PathInits.DIRECT2);

    public QExerciseDivision(String variable) {
        this(ExerciseDivision.class, forVariable(variable), INITS);
    }

    public QExerciseDivision(Path<? extends ExerciseDivision> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QExerciseDivision(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QExerciseDivision(PathMetadata metadata, PathInits inits) {
        this(ExerciseDivision.class, metadata, inits);
    }

    public QExerciseDivision(Class<? extends ExerciseDivision> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.routine = inits.isInitialized("routine") ? new QRoutine(forProperty("routine")) : null;
    }

}

