package com.example.activememory.record.presentation.command;

import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.record.presentation.command.dto.request.CreateRecordReqDto;
import com.example.activememory.record.presentation.command.dto.request.UpdateRecordReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/records")
@RestController
@Tag(name = "기록 Command API", description = "기록 관련 생성, 수정, 삭제 등에 대한 설명")
public class RecordCommandController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "기록을 생성하는 API")
    public ResponseEntity<SuccessResDto<Object>> createRecord(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @Valid @RequestBody CreateRecordReqDto dto
    ) {
        // TODO: res 값 설정 필요
        return ApiResponseUtil.success(null, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "기록을 수정하는 API", description = "RFC 7396 기준 적용")
    public  ResponseEntity<SuccessResDto<Object>> updateRecord(
            @Parameter(name = "id", description = "record id", required = true)
            @PathVariable Long id,

            @Valid @RequestBody UpdateRecordReqDto dto
    ) {
        return ApiResponseUtil.success(null, HttpStatus.OK);
    }

    @PatchMapping("/{recordId}/metric/{metricId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "기록에 포함된 개별 운동에 대한 정보를 수정하는 API")
    public  ResponseEntity<SuccessResDto<Object>> updateRecordMetric(
            @Parameter(name = "recordId", description = "record id", required = true)
            @PathVariable Long recordId,

            @Parameter(name = "metricId", description = "record Metric id", required = true)
            @PathVariable Long metricId
    ) {
        // TODO: req Dto 정의 진행 필요

        return ApiResponseUtil.success(null, HttpStatus.OK);
    }
}
