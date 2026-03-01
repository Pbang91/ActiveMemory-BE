package com.example.activememory.inventory.application.query;

import com.example.activememory.account.user.domain.vo.UserId;
import com.example.activememory.inventory.application.query.model.MyGymReadModel;
import com.example.activememory.inventory.domain.entity.QMyGym;
import com.example.activememory.reference.domain.gym.entity.QGym;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InventoryQueryRepository {
    private final JPAQueryFactory queryFactory;
    private final QMyGym myGym = QMyGym.myGym;
    private final QGym gym = QGym.gym;

    public List<MyGymReadModel> findMyGymByUserId(Long userId) {
        return queryFactory.select(
                        Projections.constructor(
                                MyGymReadModel.class,
                                myGym.id,
                                gym.name,
                                gym.address
                        )
                )
                .from(myGym)
                .join(gym).on(matchGymId())
                .where(
                        myGym.userId.eq(UserId.of(userId)),
                        myGym.isActive.isTrue()
                )
                .fetch();
    }

    private BooleanExpression matchGymId() {
        return Expressions.booleanTemplate("{0} = {1}", myGym.gymId, gym.id);
    }
}
