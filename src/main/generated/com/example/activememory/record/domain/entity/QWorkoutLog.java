package com.example.activememory.record.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkoutLog is a Querydsl query type for WorkoutLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkoutLog extends EntityPathBase<WorkoutLog> {

    private static final long serialVersionUID = -1714594641L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkoutLog workoutLog = new QWorkoutLog("workoutLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Long> performableId = createNumber("performableId", Long.class);

    public final EnumPath<com.example.activememory.global.enums.PerformableType> performableType = createEnum("performableType", com.example.activememory.global.enums.PerformableType.class);

    public final QSession session;

    public final StringPath snapshotBodyPartCode = createString("snapshotBodyPartCode");

    public final StringPath snapshotName = createString("snapshotName");

    public QWorkoutLog(String variable) {
        this(WorkoutLog.class, forVariable(variable), INITS);
    }

    public QWorkoutLog(Path<? extends WorkoutLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWorkoutLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWorkoutLog(PathMetadata metadata, PathInits inits) {
        this(WorkoutLog.class, metadata, inits);
    }

    public QWorkoutLog(Class<? extends WorkoutLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.session = inits.isInitialized("session") ? new QSession(forProperty("session")) : null;
    }

}

