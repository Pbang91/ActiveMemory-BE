package com.example.activememory.account.auth.presentation.command;

import com.example.activememory.account.auth.application.command.AuthCommandService;
import com.example.activememory.account.auth.application.command.dto.TokenResponse;
import com.example.activememory.account.auth.presentation.command.dto.AuthTokenResponse;
import com.example.activememory.account.auth.presentation.command.dto.LoginRequest;
import com.example.activememory.global.api.Api;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.swagger.annotation.ExceptionData;
import com.example.activememory.global.swagger.annotation.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth Command API")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthCommandController {
    private final AuthCommandService authCommandService;

    public AuthCommandController(AuthCommandService authCommandService) {
        this.authCommandService = authCommandService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirements
    @Operation(
            summary = "로그인 API", description = "최종 수정일: 2026.01.03\n\nEmail, OAuth를 모두 지원합니다"
    )
    @ExceptionResponse(value = {
            @ExceptionData(code = ExceptionCode.INVALID_AUTH_TYPE),
            @ExceptionData(code = ExceptionCode.INVALID_PARAMETER, details = "이메일과 비밀번호는 필수입니다"),
            @ExceptionData(code = ExceptionCode.INVALID_USER)
    })
    public ResponseEntity<SuccessResDto<AuthTokenResponse>> login(@RequestBody @Valid LoginRequest request) {
        TokenResponse result = authCommandService.login(request.toCommand());

        return Api.success(
                new AuthTokenResponse(result.accessToken(), result.refreshToken()),
                HttpStatus.OK
        );
    }
}
