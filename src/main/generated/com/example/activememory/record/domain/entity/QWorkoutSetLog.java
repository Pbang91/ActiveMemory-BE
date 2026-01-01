package com.example.activememory.record.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkoutSetLog is a Querydsl query type for WorkoutSetLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkoutSetLog extends EntityPathBase<WorkoutSetLog> {

    private static final long serialVersionUID = 748734007L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkoutSetLog workoutSetLog = new QWorkoutSetLog("workoutSetLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCompleted = createBoolean("isCompleted");

    public final NumberPath<Integer> reps = createNumber("reps", Integer.class);

    public final NumberPath<Integer> setNum = createNumber("setNum", Integer.class);

    public final NumberPath<java.math.BigDecimal> weight = createNumber("weight", java.math.BigDecimal.class);

    public final QWorkoutLog workoutLog;

    public QWorkoutSetLog(String variable) {
        this(WorkoutSetLog.class, forVariable(variable), INITS);
    }

    public QWorkoutSetLog(Path<? extends WorkoutSetLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWorkoutSetLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWorkoutSetLog(PathMetadata metadata, PathInits inits) {
        this(WorkoutSetLog.class, metadata, inits);
    }

    public QWorkoutSetLog(Class<? extends WorkoutSetLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.workoutLog = inits.isInitialized("workoutLog") ? new QWorkoutLog(forProperty("workoutLog"), inits.get("workoutLog")) : null;
    }

}

