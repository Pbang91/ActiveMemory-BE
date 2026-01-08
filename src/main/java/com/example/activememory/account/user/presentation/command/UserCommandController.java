package com.example.activememory.account.user.presentation.command;

import com.example.activememory.account.user.application.command.UserCommandService;
import com.example.activememory.account.user.presentation.command.dto.RegisterUserRequest;
import com.example.activememory.account.user.presentation.command.dto.RegisterUserResponse;
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

@Tag(name = "User Command API")
@RestController
@RequestMapping("/api/v1/users")
public class UserCommandController {
    private final UserCommandService userCommandService;

    public UserCommandController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirements
    @Operation(
            summary = "이메일 회원가입을 진행하는 API", description = "최종 수정일: 2026.01.04"
    )
    @ExceptionResponse(value = {
            @ExceptionData(code = ExceptionCode.INVALID_PARAMETER),
            @ExceptionData(code = ExceptionCode.ALREADY_EXISTS_USER),
    })
    public ResponseEntity<SuccessResDto<RegisterUserResponse>> register(@RequestBody @Valid RegisterUserRequest request) {
        Long userId = userCommandService.register(request.toCommand());

        return Api.success(new RegisterUserResponse(userId), HttpStatus.CREATED);
    }
}
