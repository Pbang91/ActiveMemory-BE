package com.example.activememory.inventory.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomMachine is a Querydsl query type for CustomMachine
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomMachine extends EntityPathBase<CustomMachine> {

    private static final long serialVersionUID = 354490477L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomMachine customMachine = new QCustomMachine("customMachine");

    public final com.example.activememory.global.entity.QBaseTimeEntity _super = new com.example.activememory.global.entity.QBaseTimeEntity(this);

    public final SimplePath<com.example.activememory.reference.domain.vo.BodyPartCode> bodyPartCode = createSimple("bodyPartCode", com.example.activememory.reference.domain.vo.BodyPartCode.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final ListPath<CustomMachineMuscle, QCustomMachineMuscle> muscles = this.<CustomMachineMuscle, QCustomMachineMuscle>createList("muscles", CustomMachineMuscle.class, QCustomMachineMuscle.class, PathInits.DIRECT2);

    public final QMyGym myGym;

    public final StringPath name = createString("name");

    public final SimplePath<com.example.activememory.reference.domain.vo.StandardExerciseId> standardExerciseId = createSimple("standardExerciseId", com.example.activememory.reference.domain.vo.StandardExerciseId.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCustomMachine(String variable) {
        this(CustomMachine.class, forVariable(variable), INITS);
    }

    public QCustomMachine(Path<? extends CustomMachine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomMachine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomMachine(PathMetadata metadata, PathInits inits) {
        this(CustomMachine.class, metadata, inits);
    }

    public QCustomMachine(Class<? extends CustomMachine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.myGym = inits.isInitialized("myGym") ? new QMyGym(forProperty("myGym")) : null;
    }

}

