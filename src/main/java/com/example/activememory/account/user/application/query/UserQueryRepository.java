package com.example.activememory.account.user.application.query;

import com.example.activememory.account.domain.entity.QUser;
import com.example.activememory.account.user.application.query.model.AuthUserReadModel;
import com.example.activememory.account.user.domain.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final JPAQueryFactory query;
    private final QUser user = QUser.user;

    public Optional<AuthUserReadModel> findUserByEmail(String email) {
        return Optional.ofNullable(
                query.select(
                        Projections.constructor(
                                AuthUserReadModel.class,
                                user.id,
                                user.email,
                                user.password
                        )).where(user.email.eq(email)).fetchOne()
        );
    }
}
