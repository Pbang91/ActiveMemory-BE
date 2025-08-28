package com.example.activememory.user.presentation.command;

import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.user.presentation.command.dto.request.CreateMyRoutineReqDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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
    @SecurityRequirements
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "ddd")
    public ResponseEntity<Void> createMyRoutine(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @Valid @RequestBody CreateMyRoutineReqDto dto
    ) {
        UUID userId = userDetail.userId();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
