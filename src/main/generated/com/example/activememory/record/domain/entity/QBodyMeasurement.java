package com.example.activememory.record.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBodyMeasurement is a Querydsl query type for BodyMeasurement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBodyMeasurement extends EntityPathBase<BodyMeasurement> {

    private static final long serialVersionUID = -980875150L;

    public static final QBodyMeasurement bodyMeasurement = new QBodyMeasurement("bodyMeasurement");

    public final NumberPath<java.math.BigDecimal> bodyFat = createNumber("bodyFat", java.math.BigDecimal.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> measuredDate = createDate("measuredDate", java.time.LocalDate.class);

    public final NumberPath<java.math.BigDecimal> skeletalMuscle = createNumber("skeletalMuscle", java.math.BigDecimal.class);

    public final SimplePath<com.example.activememory.account.domain.vo.UserId> userId = createSimple("userId", com.example.activememory.account.domain.vo.UserId.class);

    public final NumberPath<java.math.BigDecimal> weight = createNumber("weight", java.math.BigDecimal.class);

    public QBodyMeasurement(String variable) {
        super(BodyMeasurement.class, forVariable(variable));
    }

    public QBodyMeasurement(Path<? extends BodyMeasurement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBodyMeasurement(PathMetadata metadata) {
        super(BodyMeasurement.class, metadata);
    }

}

