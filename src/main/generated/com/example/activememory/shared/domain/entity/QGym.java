package com.example.activememory.shared.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGym is a Querydsl query type for Gym
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGym extends EntityPathBase<Gym> {

    private static final long serialVersionUID = 2110324871L;

    public static final QGym gym = new QGym("gym");

    public final com.example.activememory.global.entity.QBaseTimeEntity _super = new com.example.activememory.global.entity.QBaseTimeEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deleteAt = createDateTime("deleteAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QGym(String variable) {
        super(Gym.class, forVariable(variable));
    }

    public QGym(Path<? extends Gym> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGym(PathMetadata metadata) {
        super(Gym.class, metadata);
    }

}

