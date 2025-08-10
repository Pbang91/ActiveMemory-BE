package com.example.activememory.user.presentation.query;

import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.user.application.query.dto.response.UserResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/users")
@RestController
@Tag(name = "회원 Query API", description = "회원 화면에서 전용 API 설명서")
public class UserQueryController {
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "회원 본인 정보를 확인하는 API")
    public ResponseEntity<SuccessResDto<UserResDto>> getCurrentUser(
            @AuthenticationPrincipal CustomUserDetail userDetails
    ) {
        UUID userId = userDetails.userId();

        return ApiResponseUtil.success(null, HttpStatus.OK);
    }
}
