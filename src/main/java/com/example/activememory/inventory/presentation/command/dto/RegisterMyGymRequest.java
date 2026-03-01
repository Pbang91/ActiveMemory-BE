package com.example.activememory.inventory.presentation.command.dto;

import com.example.activememory.inventory.application.command.dto.RegisterMyGymCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RegisterMyGymRequest(
        @NotBlank(message = "제공자 id는 필수입니다")
        @Schema(description = "서버에서 제공한 provider id", example = "kakao:dmkmk123")
        String providerId,

        @NotBlank(message = "체육관 이름은 필수입니다")
        @Schema(description = "체육관명", example = "스포애니 합정점")
        String name,

        @Schema(description = "체육관 도로명 주소", example = "서울시 마포구 마포로 15길")
        String roadAddress,

        @NotBlank(message = "체육관 주소는 필수입니다")
        @Schema(description = "체육관 일반 주소", example = "서울시 마포구 마포동 115")
        String address,

        @Schema(description = "체육관 전화번호", example = "0212345678")
        String phone,

        @NotBlank(message = "지도상 경도는 필수값입니다")
        @Schema(description = "체육관 경도", example = "136.123456")
        String x,

        @NotBlank(message = "지도상 위도는 필수값입니다")
        @Schema(description = "체육관 위도", example = "90.1234566")
        String y
) {
    public RegisterMyGymCommand toCommand() {
        return new RegisterMyGymCommand(providerId, name, roadAddress, address, phone, x, y);
    }
}
