package com.example.activememory.feed.presentation;

import com.example.activememory.feed.application.dto.response.GetFeedResDto;
import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.api.PagePayload;
import com.example.activememory.global.api.PageQuery;
import com.example.activememory.global.api.SuccessResDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirements
@RequestMapping("/api/v1/feeds")
@RestController
@Tag(name = "피드 Query API", description = "피드와 연관된 화면에서 필요한 정보 전달 설명")
public class FeedQueryController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "피드 목록을 조회하는 API", description = "비회원도 가능한 기능")
    public ResponseEntity<SuccessResDto<PagePayload<GetFeedResDto>>> getFeedList(
            @ModelAttribute PageQuery pageQuery
    ) {
        return ApiResponseUtil.success(null, HttpStatus.OK);
    }
}
