package com.example.activememory.record.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSession is a Querydsl query type for Session
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSession extends EntityPathBase<Session> {

    private static final long serialVersionUID = 2019390030L;

    public static final QSession session = new QSession("session");

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SimplePath<com.example.activememory.inventory.domain.vo.MyGymId> myGymId = createSimple("myGymId", com.example.activememory.inventory.domain.vo.MyGymId.class);

    public final SimplePath<com.example.activememory.planning.domain.vo.RoutineId> routineId = createSimple("routineId", com.example.activememory.planning.domain.vo.RoutineId.class);

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    public final SimplePath<com.example.activememory.account.user.domain.vo.UserId> userId = createSimple("userId", com.example.activememory.account.user.domain.vo.UserId.class);

    public final ListPath<WorkoutLog, QWorkoutLog> workoutLogs = this.<WorkoutLog, QWorkoutLog>createList("workoutLogs", WorkoutLog.class, QWorkoutLog.class, PathInits.DIRECT2);

    public QSession(String variable) {
        super(Session.class, forVariable(variable));
    }

    public QSession(Path<? extends Session> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSession(PathMetadata metadata) {
        super(Session.class, metadata);
    }

}

