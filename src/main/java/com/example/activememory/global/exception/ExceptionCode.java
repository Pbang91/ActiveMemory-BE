package com.example.activememory.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    USER_FORBIDDEN("0001", "권한이 없는 사용자입니다", HttpStatus.FORBIDDEN),
    TOKEN_INVALID("0002", "유효하지 않은 토큰입니다", HttpStatus.UNAUTHORIZED),
    INVALID_PARAMETER("0003", "잘못된 요청입니다", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;

    ExceptionCode(String code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
