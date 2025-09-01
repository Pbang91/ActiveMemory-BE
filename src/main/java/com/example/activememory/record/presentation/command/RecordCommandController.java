package com.example.activememory.record.presentation.command;

import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.record.presentation.command.dto.request.CreateRecordReqDto;
import com.example.activememory.record.presentation.command.dto.request.UpdateRecordMetricItemReqDto;
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
    public ResponseEntity<Void> createRecord(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @Valid @RequestBody CreateRecordReqDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "기록을 수정하는 API", description = "RFC 7396 기준 적용")
    public ResponseEntity<Void> updateRecord(
            @Parameter(name = "id", description = "record id", required = true)
            @PathVariable Long id,

            @Valid @RequestBody UpdateRecordReqDto dto
    ) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{recordId}/metrics/{metricId}/items/{set}", consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "기록에 포함된 개별 운동에 대한 정보를 수정하는 API", description = "RFC 7396 기준 적용")
    public ResponseEntity<Void> updateRecordMetricItem(
            @Parameter(name = "recordId", description = "record id", required = true)
            @PathVariable Long recordId,

            @Parameter(name = "metricId", description = "record Metric id", required = true)
            @PathVariable Long metricId,

            @Parameter(name = "set", description = "수정하려고 하는 운동 세트", required = true)
            @PathVariable Integer set,

            @Valid @RequestBody UpdateRecordMetricItemReqDto dto
    ) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "특정 기록을 삭제하는 API")
    public ResponseEntity<Void> deleteRecord(
            @Parameter(name = "id", description = "record id", required = true)
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
