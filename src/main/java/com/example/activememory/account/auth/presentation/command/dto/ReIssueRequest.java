package com.example.activememory.account.auth.presentation.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

public record ReIssueRequest(
        @NotEmpty
        @Schema(description = "로그인 시 발급받은 refreshToken", example = "nioajnjkldasnljkf")
        String refreshToken
) {
}
