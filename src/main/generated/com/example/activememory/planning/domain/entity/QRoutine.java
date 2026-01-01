package com.example.activememory.planning.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoutine is a Querydsl query type for Routine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoutine extends EntityPathBase<Routine> {

    private static final long serialVersionUID = 1722809608L;

    public static final QRoutine routine = new QRoutine("routine");

    public final com.example.activememory.global.entity.QBaseTimeEntity _super = new com.example.activememory.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> currentDivisionSequence = createNumber("currentDivisionSequence", Integer.class);

    public final StringPath description = createString("description");

    public final ListPath<ExerciseDivision, QExerciseDivision> exerciseDivisions = this.<ExerciseDivision, QExerciseDivision>createList("exerciseDivisions", ExerciseDivision.class, QExerciseDivision.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final SimplePath<com.example.activememory.account.domain.vo.UserId> userId = createSimple("userId", com.example.activememory.account.domain.vo.UserId.class);

    public QRoutine(String variable) {
        super(Routine.class, forVariable(variable));
    }

    public QRoutine(Path<? extends Routine> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRoutine(PathMetadata metadata) {
        super(Routine.class, metadata);
    }

}

