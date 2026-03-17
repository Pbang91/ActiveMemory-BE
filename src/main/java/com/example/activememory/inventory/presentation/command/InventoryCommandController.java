package com.example.activememory.inventory.presentation.command;

import com.example.activememory.global.api.Api;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.global.swagger.annotation.ExceptionData;
import com.example.activememory.global.swagger.annotation.ExceptionResponse;
import com.example.activememory.inventory.application.command.service.InventoryCommandService;
import com.example.activememory.inventory.presentation.command.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @Operation(summary = "내 체육관을 등록하는 API", description = "최종 수정일: 2026.03.02")
    @ExceptionResponse(value = {@ExceptionData(code = ExceptionCode.ALREADY_EXIST_MY_GYM)})
    public ResponseEntity<SuccessResDto<RegisterMyGymResponse>> registerMyGym(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody @Valid RegisterMyGymRequest request
    ) {
        Long userId = userDetail.userId();
        Long myGymId = service.registerMyGym(request.toCommand(userId));

        return Api.success(new RegisterMyGymResponse(myGymId), HttpStatus.CREATED);
    }

    @PostMapping("/gym/{myGymId}/machine")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "체육관의 기구를 등록하는 API", description = "최종 수정일: 2026.03.02")
    @ExceptionResponse(value = {@ExceptionData(code = ExceptionCode.INVALID_MY_GYM)})
    public ResponseEntity<SuccessResDto<RegisterOrUpdateMyGymMachineResponse>> registerMyGymMachine(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @Parameter(name = "myGymId", example = "3", description = "내가 등록한 체육관 id", required = true)
            @PathVariable Long myGymId,
            @RequestBody @Valid RegisterMyGymMachineRequest request
    ) {
        Long userId = userDetail.userId();
        Long machineId = service.registerMyGymMachine(request.toCommand(userId, myGymId));

        return Api.success(new RegisterOrUpdateMyGymMachineResponse(machineId), HttpStatus.CREATED);
    }

    @PutMapping("/gym/{myGymId}/machine/{machineId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "체육관 기구를 수정하는 API", description = "최종 수정일: 2026.03.14")
    public ResponseEntity<SuccessResDto<RegisterOrUpdateMyGymMachineResponse>> updateMyGymMachine(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @Parameter(name = "myGymId", example = "3", description = "내가 등록한 체육관 id", required = true)
            @PathVariable Long myGymId,
            @Parameter(name = "machineId", example = "24", description = "내가 등록한 기구 id", required = true)
            @PathVariable Long machineId,
            @RequestBody @Valid UpdateMyGymMachineRequest request
    ) {
        Long userId = userDetail.userId();
        service.updateMyGymMachine(request.toCommand(userId, myGymId, machineId));

        return Api.success(new RegisterOrUpdateMyGymMachineResponse(machineId), HttpStatus.CREATED);
    }
}
