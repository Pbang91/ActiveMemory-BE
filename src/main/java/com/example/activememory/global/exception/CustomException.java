package com.example.activememory.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ExceptionCode exceptionCode;
    private String details;

    public CustomException(ExceptionCode exceptionCode) {
        super(exceptionCode.getDescription());
        this.exceptionCode = exceptionCode;
    }

    public CustomException(ExceptionCode exceptionCode, String details) {
        super(exceptionCode.getDescription() + " " + details);
        this.exceptionCode = exceptionCode;
        this.details = details;
    }
}
