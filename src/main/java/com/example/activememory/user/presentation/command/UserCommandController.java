package com.example.activememory.user.presentation.command;

import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.user.application.command.dto.response.AuthTokenResDto;
import com.example.activememory.user.presentation.command.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@RequestMapping("/api/v1/users")
@RestController
@Tag(name = "회원 Command API", description = "회원 생성, 수정, 삭제(탈퇴) 등에 대한 설명")
public class UserCommandController {
    @SecurityRequirements
    @PostMapping("/oauth/kakao")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(
            summary = "카카오에서 발급받은 인증 토큰을 이용해 서비스 인증을 진행하는 API",
            description = "인증이 완료되면, 회원 등록 후 클라이언트의 지정된 주소로 임시 코드가 담긴 후 redirect를 진행합니다"
    )
    public RedirectView authWithKakaoToken(
            @Valid @RequestBody AuthWithKakaoTokenDto dto
    ) {
        return new RedirectView("/");
    }

    @SecurityRequirements
    @GetMapping("/oauth/kakao/callback")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(
            summary = "카카오 계정을 이용해 발급받은 인증 코드를 이용해 서비스 인증을 진행하는 API",
            description = "인증이 완료되면, 회원 등록 후 클라이언트의 지정된 주소로 임시 코드가 담긴 후 redirect를 진행합니다"
    )
    public RedirectView callBackKakaoAccountAuthentication(
            @ModelAttribute CallBackKakaoAccountAuthenticationDto dto
    ) {
        return new RedirectView("/");
    }

    @SecurityRequirements
    @PostMapping("/oauth/apple")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(
            summary = "애플에서 발급받은 자격증명을 이용해 서비스 인증을 진행하는 API",
            description = "인증이 완료되면, 회원 등록 후 클라이언트의 지정된 주소로 임시 코드가 담긴 후 redirect를 진행합니다"
    )
    public RedirectView authWithAppleCredential(
            @Valid @RequestBody AuthWithAppleCredentialDto dto
    ) {
        return new RedirectView("/");
    }

    @SecurityRequirements
    @PostMapping(value = "/oauth/apple/callback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(
            summary = "애플 계정을 이용해 발급받은 인증 코드를 이용해 서비스 인증을 진행하는 API",
            description = "인증이 완료되면, 회원 등록 후 클라이언트의 지정된 주소로 임시 코드가 담긴 후 redirect를 진행합니다"
    )
    public RedirectView callBackAppleAccountAuthentication(
            @ModelAttribute CallBackAppleAccountAuthentication dto
    ) {
        return new RedirectView("/");
    }

    @SecurityRequirements
    @GetMapping("/auth/token")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "서비스 Auth Token을 발급받는 API",
            description = "Access 및 Refresh Token을 발급합니다")
    public ResponseEntity<SuccessResDto<AuthTokenResDto>> authorizeUseWithCode(
            @Parameter(name = "code", description = "auth code", required = true)
            @RequestParam(name = "code") String code
    ) {
        return ApiResponseUtil.success(new AuthTokenResDto("", ""), HttpStatus.CREATED);
    }

    @SecurityRequirements
    @PostMapping(value = "/auth/token/refresh")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "RefreshToken을 이용해 Auth Token을 갱신하는 API")
    public ResponseEntity<SuccessResDto<AuthTokenResDto>> refresh(
            @Valid @RequestBody RefreshTokenDto dto
    ) {
        return ApiResponseUtil.success(new AuthTokenResDto("", ""), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "회원탈퇴를 진행하는 API", description = "연동된 OAuth 정보도 함께 해지를 진행합니다")
    public ResponseEntity<Void> deleteUser(
            @AuthenticationPrincipal CustomUserDetail userDetails
    ) {
        UUID userId = userDetails.userId();

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "디바이스에서 로그아웃을 진행하는 API", description = "디바이스 정보 및 Auth Token 정보를 정리합니다")
    public ResponseEntity<Void> logout(
            @AuthenticationPrincipal CustomUserDetail userDetails
    ) {
        String deviceId = userDetails.deviceId();

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/me/profile")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "회원이 프로필 정보를 수정하는 API")
    public ResponseEntity<Void>  updateProfile(
            @AuthenticationPrincipal CustomUserDetail userDetails,
            @Valid UpdateProfileDto dto
    ) {
        UUID userId = userDetails.userId();

        return ResponseEntity.noContent().build();
    }
}
