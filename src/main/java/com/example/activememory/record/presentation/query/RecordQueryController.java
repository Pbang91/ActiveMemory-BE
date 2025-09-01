package com.example.activememory.record.presentation.query;

import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.api.PagePayload;
import com.example.activememory.global.api.PageQuery;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.record.application.query.dto.response.GetRecordResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/records")
@RestController
@Tag(name = "기록 Query API", description = "기록과 연관된 화면에서 필요한 정보 전달 설명")
public class RecordQueryController {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "기록 목록을 조회하는 API")
    public ResponseEntity<SuccessResDto<PagePayload<GetRecordResDto>>> getRecords(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @ModelAttribute PageQuery pageQuery
    ) {
        UUID userId = userDetail.userId();

        return ApiResponseUtil.success(null, HttpStatus.OK);
    }
}
