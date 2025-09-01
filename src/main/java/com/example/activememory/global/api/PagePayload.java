package com.example.activememory.global.api;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

public record PagePayload<T>(
        @ArraySchema(
                schema = @Schema(
                        description = "content"
                ),
                arraySchema = @Schema(
                        requiredMode = Schema.RequiredMode.REQUIRED,
                        nullable = true
                ),
                minItems = 0
        )
        List<T> content,

        @Schema(description = "page information", requiredMode = Schema.RequiredMode.REQUIRED, nullable = true)
        Pagination pagination
) {
    public static <T> PagePayload<T> from(Page<T> page) {
        return new PagePayload<>(
                page.getContent(),
                Pagination.from(page)
        );
    }
}
