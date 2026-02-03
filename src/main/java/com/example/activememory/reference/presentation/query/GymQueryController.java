package com.example.activememory.reference.presentation.query;

import com.example.activememory.global.api.Api;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.reference.application.query.GymQueryService;
import com.example.activememory.reference.application.query.model.GymReadModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Reference Query API")
@RestController
@RequestMapping("/api/v1/references/gym")
public class GymQueryController {
    private final GymQueryService service;

    public GymQueryController(GymQueryService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "체육관 주소 정보를 전달하는 API", description = "최종 수정일: 2026.02.03")
    public ResponseEntity<SuccessResDto<List<GymReadModel>>> getGyms(
            @Parameter(name = "q", required = true, description = "체육관 검색을 위해 사용하는 키워드", example = "레몬핏 노원")
            @RequestParam(name = "q") String q
    ) {
        return Api.success(service.search(q), HttpStatus.OK);
    }
}
