package com.example.activememory.user.infrastructure.persistence;

import com.example.activememory.global.share.id.UserId;
import com.example.activememory.user.domain.user.entity.User;
import com.example.activememory.user.domain.user.enums.OAuthType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserInternal extends JpaRepository<User, UserId> {
    Optional<User> findByOauthInfo_IdAndOauthInfo_Type(String id, OAuthType type);
}
