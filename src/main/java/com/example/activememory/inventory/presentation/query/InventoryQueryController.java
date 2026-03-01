package com.example.activememory.inventory.presentation.query;

import com.example.activememory.global.api.Api;
import com.example.activememory.global.api.SuccessResDto;
import com.example.activememory.global.security.CustomUserDetail;
import com.example.activememory.inventory.application.query.InventoryQueryService;
import com.example.activememory.inventory.application.query.model.MyGymReadModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Inventory Query API")
@RestController
@RequestMapping("/api/v1/inventories")
public class InventoryQueryController {
    private final InventoryQueryService service;

    public InventoryQueryController(InventoryQueryService service) {
        this.service = service;
    }

    @GetMapping("/gym")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "내가 등록한 체육관 정보를 전달하는 API", description = "최종 수정일: 2026.03.01")
    public ResponseEntity<SuccessResDto<List<MyGymReadModel>>> getMyGym(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        return Api.success(service.getMyGym(userDetail.userId()), HttpStatus.OK);
    }
}
