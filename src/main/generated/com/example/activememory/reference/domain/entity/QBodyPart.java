package com.example.activememory.reference.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBodyPart is a Querydsl query type for BodyPart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBodyPart extends EntityPathBase<BodyPart> {

    private static final long serialVersionUID = 476203695L;

    public static final QBodyPart bodyPart = new QBodyPart("bodyPart");

    public final StringPath code = createString("code");

    public final StringPath name = createString("name");

    public QBodyPart(String variable) {
        super(BodyPart.class, forVariable(variable));
    }

    public QBodyPart(Path<? extends BodyPart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBodyPart(PathMetadata metadata) {
        super(BodyPart.class, metadata);
    }

}

