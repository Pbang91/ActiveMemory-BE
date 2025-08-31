package com.example.activememory.user.presentation.query;

import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.exception.annotation.ExceptionData;
import com.example.activememory.global.exception.annotation.ExceptionResponse;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.user.application.query.dto.response.GetMyRoutineByIdResDto;
import com.example.activememory.user.application.query.dto.response.GetMyRoutineListResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/users/my-routine")
@RestController
@Tag(name = "마이루틴 Query API", description = "마이루틴 화면 전용 API 설명서")
public class MyRoutineQueryController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "마이루틴 목록 전체를 조회하는 API",
            description = "마이루틴 목록 없을 시 Empty List 반환"
    )
    public ResponseEntity<SuccessResDto<List<GetMyRoutineListResDto>>> getMyRoutineList(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        UUID userId = userDetail.userId();

        return ApiResponseUtil.success(null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "특정 마이루틴의 정보를 조회하는 API")
    @ExceptionResponse(
            value = {
                    @ExceptionData(code = ExceptionCode.INVALID_PARAMETER)
            }
    )
    public ResponseEntity<SuccessResDto<GetMyRoutineByIdResDto>> getMyRoutineById(
            @AuthenticationPrincipal CustomUserDetail userDetail,

            @Parameter(name = "id", description = "myRoutine id", required = true)
            @PathVariable(name = "id") Long myRoutineId
    ) {
        UUID userId = userDetail.userId();

        return ApiResponseUtil.success(null, HttpStatus.OK);
    }
}
