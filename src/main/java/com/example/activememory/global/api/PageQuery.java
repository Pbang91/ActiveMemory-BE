package com.example.activememory.global.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@ParameterObject
public record PageQuery(
        @Parameter(description = "페이지 번호", example = "0")
        @Schema(defaultValue = "0", minimum = "0")
        Integer page,

        @Parameter(description = "페이지 크기", example = "20")
        @Schema(defaultValue = "10", minimum = "1", maximum = "50")
        Integer size,

        @Parameter(description = "정렬 기준", example = "createdAt")
        String sortBy,

        @Parameter(description = "정렬 방법", example = "DESC")
        @Schema(defaultValue = "ASC")
        String direction
) {
    public Pageable toPageable() {
        int p = page == null ? 0 : page;
        int s = size == null ? 10 : size;
        Sort sortSpec = Sort.unsorted();

        if (sortBy != null) {
            sortSpec = sortSpec.and(Sort.by(Sort.Direction.fromString(direction), sortBy.trim()));
        }

        return PageRequest.of(p, s, sortSpec);
    }
}
