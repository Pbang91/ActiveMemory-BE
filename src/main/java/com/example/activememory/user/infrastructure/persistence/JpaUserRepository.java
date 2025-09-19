package com.example.activememory.user.infrastructure.persistence;

import com.example.activememory.global.share.id.UserId;
import com.example.activememory.user.domain.user.entity.User;
import com.example.activememory.user.domain.user.repository.UserRepository;
import com.example.activememory.user.domain.user.vo.OAuthInfo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {
    private final JpaUserInternal userInternal;

    public JpaUserRepository(JpaUserInternal userInternal) {
        this.userInternal = userInternal;
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return userInternal.findById(userId);
    }

    @Override
    public Optional<User> findByOAuthInfo(OAuthInfo oauthInfo) {
        return userInternal.findByOauthInfo_IdAndOauthInfo_Type(oauthInfo.getId(), oauthInfo.getType());
    }

    @Override
    public User save(User user) {
        return userInternal.save(user);
    }
}
