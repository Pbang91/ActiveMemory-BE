package com.example.activememory.inventory.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomMachineMuscle is a Querydsl query type for CustomMachineMuscle
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomMachineMuscle extends EntityPathBase<CustomMachineMuscle> {

    private static final long serialVersionUID = -1644036034L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomMachineMuscle customMachineMuscle = new QCustomMachineMuscle("customMachineMuscle");

    public final QCustomMachine customMachine;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SimplePath<com.example.activememory.reference.domain.vo.MuscleId> muscleId = createSimple("muscleId", com.example.activememory.reference.domain.vo.MuscleId.class);

    public final EnumPath<com.example.activememory.reference.domain.enums.MuscleRole> role = createEnum("role", com.example.activememory.reference.domain.enums.MuscleRole.class);

    public QCustomMachineMuscle(String variable) {
        this(CustomMachineMuscle.class, forVariable(variable), INITS);
    }

    public QCustomMachineMuscle(Path<? extends CustomMachineMuscle> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomMachineMuscle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomMachineMuscle(PathMetadata metadata, PathInits inits) {
        this(CustomMachineMuscle.class, metadata, inits);
    }

    public QCustomMachineMuscle(Class<? extends CustomMachineMuscle> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customMachine = inits.isInitialized("customMachine") ? new QCustomMachine(forProperty("customMachine"), inits.get("customMachine")) : null;
    }

}

