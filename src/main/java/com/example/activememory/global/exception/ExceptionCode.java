package com.example.activememory.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {
    FORBIDDEN_USER("0001", "권한이 없는 사용자입니다", HttpStatus.FORBIDDEN),
    INVALID_TOKEN("0002", "유효하지 않은 토큰입니다", HttpStatus.UNAUTHORIZED),
    INVALID_USER("0003", "유효하지 않은 사용자입니다", HttpStatus.BAD_REQUEST),
    INVALID_AUTH_TYPE("0004", "지원하지 않는 인증수단입니다", HttpStatus.BAD_REQUEST),
    INVALID_PARAMETER("9998", "잘못된 요청입니다", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("9999", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;

    ExceptionCode(String code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
