package com.example.activememory.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ExceptionCode exceptionCode;
    private String details;

    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getDescription());
        this.exceptionCode = exceptionCode;
    }

    public BusinessException(ExceptionCode exceptionCode, String details) {
        super(exceptionCode.getDescription() + " " + details);
        this.exceptionCode = exceptionCode;
        this.details = details;
    }
}
