package com.example.activememory.reference.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStandardExercise is a Querydsl query type for StandardExercise
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStandardExercise extends EntityPathBase<StandardExercise> {

    private static final long serialVersionUID = 1745074095L;

    public static final QStandardExercise standardExercise = new QStandardExercise("standardExercise");

    public final SimplePath<com.example.activememory.reference.domain.vo.BodyPartCode> bodyPartCode = createSimple("bodyPartCode", com.example.activememory.reference.domain.vo.BodyPartCode.class);

    public final StringPath description = createString("description");

    public final ListPath<StandardExerciseMuscle, QStandardExerciseMuscle> exerciseMuscles = this.<StandardExerciseMuscle, QStandardExerciseMuscle>createList("exerciseMuscles", StandardExerciseMuscle.class, QStandardExerciseMuscle.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath type = createString("type");

    public QStandardExercise(String variable) {
        super(StandardExercise.class, forVariable(variable));
    }

    public QStandardExercise(Path<? extends StandardExercise> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStandardExercise(PathMetadata metadata) {
        super(StandardExercise.class, metadata);
    }

}

