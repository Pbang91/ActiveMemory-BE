package com.example.activememory.account.auth.application.port;

import com.example.activememory.account.auth.application.command.dto.LoginCommand;
import com.example.activememory.account.auth.domain.enums.AuthType;
import com.example.activememory.account.auth.domain.entity.AuthTargetUser;

public interface AuthStrategy {
    boolean supports(AuthType authType);

    AuthTargetUser authenticate(LoginCommand command);
}
