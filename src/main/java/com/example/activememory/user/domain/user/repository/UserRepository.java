package com.example.activememory.user.domain.user.repository;

import com.example.activememory.global.share.id.UserId;
import com.example.activememory.user.domain.user.entity.User;
import com.example.activememory.user.domain.user.vo.OAuthInfo;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(UserId id);

    Optional<User> findByOAuthInfo(OAuthInfo oauthInfo);

    User save(User user);
}
