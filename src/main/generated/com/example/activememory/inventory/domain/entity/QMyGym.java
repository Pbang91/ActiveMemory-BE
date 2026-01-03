package com.example.activememory.inventory.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMyGym is a Querydsl query type for MyGym
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMyGym extends EntityPathBase<MyGym> {

    private static final long serialVersionUID = -812041146L;

    public static final QMyGym myGym = new QMyGym("myGym");

    public final com.example.activememory.global.entity.QBaseTimeEntity _super = new com.example.activememory.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final SimplePath<com.example.activememory.shared.domain.vo.GymId> gymId = createSimple("gymId", com.example.activememory.shared.domain.vo.GymId.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final SimplePath<com.example.activememory.account.user.domain.vo.UserId> userId = createSimple("userId", com.example.activememory.account.user.domain.vo.UserId.class);

    public QMyGym(String variable) {
        super(MyGym.class, forVariable(variable));
    }

    public QMyGym(Path<? extends MyGym> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMyGym(PathMetadata metadata) {
        super(MyGym.class, metadata);
    }

}

