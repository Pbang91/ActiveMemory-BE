package com.example.activememory.account.auth.application.port;

import com.example.activememory.account.auth.domain.AuthTargetUser;

import java.util.Optional;

public interface UserPort {
    Optional<AuthTargetUser> loadUserByEmail(String email);
}
