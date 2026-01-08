package com.example.activememory.account.user.application.query;

import com.example.activememory.account.user.application.query.model.AuthUserReadModel;
import com.example.activememory.account.user.application.query.model.UserMeReadModel;
import com.example.activememory.account.user.domain.entity.QUser;
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
                                ))
                        .from(user)
                        .where(user.email.eq(email)).fetchOne()
        );
    }

    public Optional<UserMeReadModel> findMe(Long userId) {
        return Optional.ofNullable(
                query.select(
                                Projections.constructor(
                                        UserMeReadModel.class,
                                        user.id,
                                        user.email,
                                        user.profile.nickname,
                                        user.profile.bio,
                                        user.type,
                                        user.createdAt
                                ))
                        .from(user)
                        .where(user.id.eq(userId)).fetchOne()
        );
    }
}
