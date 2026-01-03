package com.example.activememory.account.auth.infrastructure.auth;

import com.example.activememory.account.auth.application.command.dto.LoginCommand;
import com.example.activememory.account.auth.application.port.AuthStrategy;
import com.example.activememory.account.auth.application.port.UserPort;
import com.example.activememory.account.auth.domain.entity.AuthTargetUser;
import com.example.activememory.global.enums.AuthType;
import com.example.activememory.global.exception.BusinessException;
import com.example.activememory.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class EmailAuthStrategy implements AuthStrategy {
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean supports(AuthType authType) {
        return authType == AuthType.EMAIL;
    }

    @Override
    public AuthTargetUser authenticate(LoginCommand command) {
        if (!StringUtils.hasText(command.email()) || !StringUtils.hasText(command.password())) {
            throw new BusinessException(ExceptionCode.INVALID_PARAMETER, "이메일과 비밀번호는 필수입니다");
        }

        AuthTargetUser user = userPort.loadUserByEmail(command.email())
                .orElseThrow(() -> new BusinessException(ExceptionCode.INVALID_USER, "가입되지 않은 이메일"));

        if (!passwordEncoder.matches(command.password(), user.password())) {
            throw new BusinessException(ExceptionCode.INVALID_USER);
        }

        return user;
    }
}
