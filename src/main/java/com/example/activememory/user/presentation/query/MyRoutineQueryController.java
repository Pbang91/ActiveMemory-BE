package com.example.activememory.user.presentation.query;

import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.security.CustomUserDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/my-routine")
@RestController
@Tag(name = "마이루틴 Query API", description = "마이루틴 화면 전용 API 설명서")
public class MyRoutineQueryController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "회원이 작성한 기록 템플릿 전체를 조회하는 API",
            description = "작성된 기록 템플릿이 없다면 Empty List 반환"
    )
    public ResponseEntity<SuccessResDto<List<?>>> getAllRecordTemplate(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        UUID userId = userDetail.userId();

        return ApiResponseUtil.success(null, HttpStatus.OK);
    }

    @GetMapping("/record-template/{templateId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "특정 템플릿 정보를 상세하게 조회하는 API")
    public ResponseEntity<SuccessResDto<Void>> getRecordTemplate(
            @AuthenticationPrincipal CustomUserDetail userDetail,

            @Parameter(name = "templateId", description = "recordTemplate id", required = true)
            @PathVariable(name = "templateId") String templateId
    ) {
        UUID userId = userDetail.userId();

        return ApiResponseUtil.success(null, HttpStatus.OK);
    }
}
