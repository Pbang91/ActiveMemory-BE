package com.example.activememory.global.api;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public record Pagination(
        @Schema(description = "현재 위치한 페이지", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        int page,

        @Schema(description = "현재 위치한 페이지의 content 수", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
        int size,

        @Schema(description = "총 content 수", example = "525", requiredMode = Schema.RequiredMode.REQUIRED)
        long totalElements,

        @Schema(description = "총 페이지 수", example = "53", requiredMode = Schema.RequiredMode.REQUIRED)
        int totalPages,

        @Schema(description = "다음 페이지 존재 여부", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
        boolean hasNext,

        @Schema(description = "이전 페이지 존재 여부", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
        boolean hasPrevious,

        @ArraySchema(
                schema = @Schema(
                        description = "정렬 기준",
                        implementation = SortSpec.class
                ),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.REQUIRED
                )
        )
        List<SortSpec> sort
) {
    public static Pagination from(Page<?> page) {
        List<SortSpec> sort = new ArrayList<>();

        page.getSort().forEach(order -> sort.add(
                new SortSpec(
                        order.getProperty(),
                        order.getDirection().name()
                )
        ));

        return new Pagination(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious(),
                sort
        );
    }
}
