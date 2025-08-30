package com.example.activememory.user.presentation.command;

import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.user.presentation.command.dto.request.CreateMyRoutineReqDto;
import com.example.activememory.user.presentation.command.dto.request.UpdateMyRoutineItemReqDto;
import com.example.activememory.user.presentation.command.dto.request.UpdateMyRoutineReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/users/my-routine")
@RestController
@Tag(name = "마이루틴 Command API", description = "회원의 마이루틴 생성, 수정, 삭제 등에 대한 설명")
public class MyRoutineCommandController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "마이루틴을 생성하는 API")
    public ResponseEntity<Void> createMyRoutine(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @Valid @RequestBody CreateMyRoutineReqDto dto
    ) {
        UUID userId = userDetail.userId();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "마이루틴을 수정하는 API", description = """
            RFC 7396 기준을 적용하는 API입니다.
            
            수정을 진행하고자 하는 필드에 값을 전달하면, 해당 필드의 데이터를 갱신합니다(null 전달 시 null로 변경).
            key를 전달하지 않는다면 무시합니다.
            
            routineList 전달 시 전달된 routineList로 확정, 변경됩니다.
            별도 변경을 위해서는 PATCH my-routine/{routineId}/items/{itemId}를 이용합니다.
            """)
    public ResponseEntity<Void> updateMyRoutine(
            @Parameter(name = "id", description = "마이루틴 고유 id", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateMyRoutineReqDto dto
    ) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{routineId}/items/{itemId}", consumes = "application/merge-patch+json")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "마이루틴의 운동항목의 내용을 수정하는 API", description = """
            RFC 7396 기준을 적용하는 API입니다.
            """)
    public ResponseEntity<Void> updateMyRoutineItem(
            @Parameter(name = "routineId", description = "마이루틴 고유 id", required = true)
            @PathVariable Long routineId,

            @Parameter(name = "itemId", description = "운동 항목 고유 id", required = true)
            @PathVariable Long itemId,

            @Valid @RequestBody UpdateMyRoutineItemReqDto dto
    ) {
        return ResponseEntity.ok().build();
    }
}
