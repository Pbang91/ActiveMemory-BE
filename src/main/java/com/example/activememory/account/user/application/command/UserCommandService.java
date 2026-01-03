package com.example.activememory.account.user.application.command;

import com.example.activememory.account.user.application.command.dto.RegisterUserCommand;
import com.example.activememory.account.user.domain.entity.User;
import com.example.activememory.account.user.domain.repository.UserRepository;
import com.example.activememory.global.enums.AuthType;
import com.example.activememory.global.exception.BusinessException;
import com.example.activememory.global.exception.ExceptionCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCommandService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCommandService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long register(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new BusinessException(ExceptionCode.ALREADY_EXISTS_USER);
        }

        String encryptedPassword = passwordEncoder.encode(command.password());

        User user = User.of(AuthType.EMAIL, command.email(), encryptedPassword, command.nickname(), command.bio());

        return userRepository.save(user).getUserId().value();
    }
}
