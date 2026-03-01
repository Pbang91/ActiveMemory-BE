package com.example.activememory.inventory.presentation.command;

import com.example.activememory.global.api.Api;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.global.swagger.annotation.ExceptionData;
import com.example.activememory.global.swagger.annotation.ExceptionResponse;
import com.example.activememory.inventory.application.command.service.InventoryCommandService;
import com.example.activememory.inventory.presentation.command.dto.RegisterMyGymRequest;
import com.example.activememory.inventory.presentation.command.dto.RegisterMyGymResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Inventory Command API")
@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryCommandController {
    private final InventoryCommandService service;

    public InventoryCommandController(InventoryCommandService inventoryCommandService) {
        this.service = inventoryCommandService;
    }

    @PostMapping("/gym")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "내 체육관을 등록하는 API", description = "최종 수정일: 2026.02.16")
    @ExceptionResponse(value = {@ExceptionData(code = ExceptionCode.ALREADY_EXIST_MY_GYM)})
    public ResponseEntity<SuccessResDto<RegisterMyGymResponse>> registerMyGym(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody @Valid RegisterMyGymRequest request
    ) {
        Long userId = userDetail.userId();

        Long myGymId = service.registerMyGym(userId, request.toCommand());

        return Api.success(new RegisterMyGymResponse(myGymId), HttpStatus.CREATED);
    }
}
