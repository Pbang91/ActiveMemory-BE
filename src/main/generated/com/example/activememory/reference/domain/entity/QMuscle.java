package com.example.activememory.reference.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMuscle is a Querydsl query type for Muscle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMuscle extends EntityPathBase<Muscle> {

    private static final long serialVersionUID = 433115627L;

    public static final QMuscle muscle = new QMuscle("muscle");

    public final SimplePath<com.example.activememory.reference.domain.vo.BodyPartCode> bodyPartCode = createSimple("bodyPartCode", com.example.activememory.reference.domain.vo.BodyPartCode.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QMuscle(String variable) {
        super(Muscle.class, forVariable(variable));
    }

    public QMuscle(Path<? extends Muscle> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMuscle(PathMetadata metadata) {
        super(Muscle.class, metadata);
    }

}

