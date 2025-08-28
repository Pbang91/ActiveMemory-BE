package com.example.activememory.user.application.query.dto.response;

import com.example.activememory.global.share.enums.Visibility;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetMyRoutineListResDto {
    @Schema(description = "MyRoutine 고유 id", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "MyRoutine 이름(제목)", example = "상체-파워루틴", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "MyRoutine 부가 설명", example = "벤치프레스 120kg 2회 1세트 하고, 3분쉬어얌 함", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Schema(description = "MyRoutine 공개(공유) 범위", example = "PUBLIC", requiredMode = Schema.RequiredMode.REQUIRED)
    private Visibility visibility;

    @Schema(description = "MyRoutine 생성일 및 시간", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createdAt;

    protected GetMyRoutineListResDto() {}

    private GetMyRoutineListResDto(
            Long id,
            String name,
            String description,
            Visibility visibility,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.createdAt = createdAt;
    }

    public static GetMyRoutineListResDto from(
            Long id,
            String name,
            String description,
            Visibility visibility,
            LocalDateTime createdAt
    ) {
        return  new GetMyRoutineListResDto(id, name, description, visibility, createdAt);
    }
}
