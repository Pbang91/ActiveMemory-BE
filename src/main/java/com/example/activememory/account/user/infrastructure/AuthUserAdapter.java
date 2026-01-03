package com.example.activememory.account.user.infrastructure;

import com.example.activememory.account.auth.application.port.UserPort;
import com.example.activememory.account.auth.domain.entity.AuthTargetUser;
import com.example.activememory.account.user.application.query.UserQueryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUserAdapter implements UserPort {
    private final UserQueryRepository userQueryRepository;

    public AuthUserAdapter(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    @Override
    public Optional<AuthTargetUser> loadUserByEmail(String email) {
        return userQueryRepository.findUserByEmail(email)
                .map(u -> new AuthTargetUser(u.userId(), u.email(), u.password()));
    }
}
