package com.example.activememory.feed.presentation;

import com.example.activememory.feed.application.dto.response.GetFeedListResDto;
import com.example.activememory.feed.application.dto.response.GetFeedResDto;
import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.api.PagePayload;
import com.example.activememory.global.api.SuccessResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirements
@RequestMapping("/api/v1/feed")
@RestController
@Tag(name = "피드 Query API", description = "피드와 연관된 화면에서 필요한 정보 전달 설명")
public class FeedQueryController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "피드 목록을 조회하는 API", description = "비회원도 가능한 기능")
    public ResponseEntity<SuccessResDto<PagePayload<GetFeedListResDto>>> getFeedList() {
        return ApiResponseUtil.success(null, HttpStatus.OK);
    }

    @GetMapping("/{recordSlug}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "피드 상세 조회를 진행하는 API", description = "비회원도 가능한 기능")
    public ResponseEntity<SuccessResDto<GetFeedResDto>> getFeed(
            @Parameter(name = "recordSlug", description = "기록에 접근하기 위한 record slug", required = true)
            @PathVariable @NotBlank String recordSlug
    ) {
        return ApiResponseUtil.success(null, HttpStatus.OK);
    }
}
