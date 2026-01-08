package com.example.activememory.account.user.presentation.query;

import com.example.activememory.account.user.application.query.UserQueryService;
import com.example.activememory.account.user.application.query.model.UserMeReadModel;
import com.example.activememory.global.api.Api;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.global.swagger.annotation.ExceptionData;
import com.example.activememory.global.swagger.annotation.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User Query API")
@RestController
@RequestMapping("/api/v1/users")
public class UserQueryController {
    private final UserQueryService service;

    public UserQueryController(UserQueryService service) {
        this.service = service;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "본인 정보를 조회하는 API", description = "최종 수정일; 2026.01.08"
    )
    @ExceptionResponse(
            value = {
                    @ExceptionData(code = ExceptionCode.INVALID_TOKEN)
            }
    )
    public ResponseEntity<SuccessResDto<UserMeReadModel>> getMe(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        return Api.success(
                service.getMe(userDetail.getUserId()),
                HttpStatus.OK
        );
    }
}
