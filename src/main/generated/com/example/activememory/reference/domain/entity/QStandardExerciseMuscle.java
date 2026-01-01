package com.example.activememory.reference.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStandardExerciseMuscle is a Querydsl query type for StandardExerciseMuscle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStandardExerciseMuscle extends EntityPathBase<StandardExerciseMuscle> {

    private static final long serialVersionUID = 1636607488L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStandardExerciseMuscle standardExerciseMuscle = new QStandardExerciseMuscle("standardExerciseMuscle");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMuscle muscle;

    public final EnumPath<com.example.activememory.reference.domain.enums.MuscleRole> role = createEnum("role", com.example.activememory.reference.domain.enums.MuscleRole.class);

    public final QStandardExercise standardExercise;

    public QStandardExerciseMuscle(String variable) {
        this(StandardExerciseMuscle.class, forVariable(variable), INITS);
    }

    public QStandardExerciseMuscle(Path<? extends StandardExerciseMuscle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStandardExerciseMuscle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStandardExerciseMuscle(PathMetadata metadata, PathInits inits) {
        this(StandardExerciseMuscle.class, metadata, inits);
    }

    public QStandardExerciseMuscle(Class<? extends StandardExerciseMuscle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.muscle = inits.isInitialized("muscle") ? new QMuscle(forProperty("muscle")) : null;
        this.standardExercise = inits.isInitialized("standardExercise") ? new QStandardExercise(forProperty("standardExercise")) : null;
    }

}

