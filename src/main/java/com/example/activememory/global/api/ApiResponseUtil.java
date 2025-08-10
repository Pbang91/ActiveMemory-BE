package com.example.activememory.global.api;

import com.example.activememory.global.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {
    public static <T>ResponseEntity<SuccessResDto<T>> success(T data, HttpStatus status) {
        SuccessResDto<T> body = new SuccessResDto<>(data);

        return ResponseEntity.status(status).body(body);
    }

    public static ResponseEntity<ExceptionResDto> reject(ExceptionCode exceptionCode, String details) {
        ExceptionResDto apiExceptionResDto = new ExceptionResDto(
                exceptionCode.getCode(),
                exceptionCode.getDescription(),
                details
        );

        return new ResponseEntity<>(apiExceptionResDto, exceptionCode.getHttpStatus());
    }
}
