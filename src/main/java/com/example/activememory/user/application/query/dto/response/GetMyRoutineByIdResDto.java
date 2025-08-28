package com.example.activememory.user.application.query.dto.response;

import com.example.activememory.global.share.enums.Visibility;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class GetMyRoutineByIdResDto {
    @Schema(description = "MyRoutine 고유 id", example = "2", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "MyRoutine 이름(제목)", example = "상체-파워루틴", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "MyRoutine 부가 설명", example = "벤치프레스 120kg 2회 1세트 하고, 3분쉬어얌 함", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;

    @Schema(description = "MyRoutine 공개(공유) 범위", example = "PUBLIC", requiredMode = Schema.RequiredMode.REQUIRED)
    private Visibility visibility;

    @ArraySchema(
            schema = @Schema(
                    description = "MyRoutine에 저장된 각 운동(루틴) 정보",
                    implementation = MyRoutineItemResDto.class,
                    requiredMode = Schema.RequiredMode.REQUIRED
            )
    )
    private List<MyRoutineItemResDto> routineList;

    protected GetMyRoutineByIdResDto() {}

    private GetMyRoutineByIdResDto(
            Long id,
            String name,
            String description,
            Visibility visibility,
            List<MyRoutineItemResDto> itemList
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.visibility = visibility;
        this.routineList = itemList;
    }

    public static GetMyRoutineByIdResDto from(
            Long myRoutineId,
            String name,
            String description,
            Visibility visibility,
            List<MyRoutineItemResDto> itemList
    ) {
        return new GetMyRoutineByIdResDto(
                myRoutineId,
                name,
                description,
                visibility,
                itemList
        );
    }
}
