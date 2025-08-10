package com.example.activememory.user.presentation.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record CallBackAppleAccountAuthentication(
        @Schema(description = "Apple accessToken을 얻기 위한 임시 코드", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String code,

        @Schema(description = "id_token. jwt 형태이며 parsing을 진행하면 애플 사용자 정보가 들어있음", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String id_token,

        @Schema(description = "state. CORS 방지", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String state,

        // format: {"name": {"firstName": String, "lastName": String}, "email": String"}
        @Schema(description = "user 정보 jsonString", requiredMode = Schema.RequiredMode.NOT_REQUIRED, nullable = true)
        String user
) {
}
