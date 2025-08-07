package com.example.activememory.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ExceptionCode errorCode;
    private String details = "";

    public CustomException(ExceptionCode data) {
        super(data.getDescription());

        this.errorCode = data;
    }

    public CustomException(ExceptionCode data, String details) {
        super(data.getDescription() + " " + details);

        this.errorCode = data;
        this.details = details;
    }
}
