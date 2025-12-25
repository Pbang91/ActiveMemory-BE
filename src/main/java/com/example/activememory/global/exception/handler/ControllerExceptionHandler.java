package com.example.activememory.global.exception.handler;

import com.example.activememory.global.api.ApiResponseUtil;
import com.example.activememory.global.api.ExceptionResDto;
import com.example.activememory.global.exception.CustomException;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.logging.CustomLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger("ERROR_LOGGER");
    private final ObjectMapper objectMapper;

    public ControllerExceptionHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
}

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResDto> handleCustomException(CustomException e) {
        return ApiResponseUtil.reject(e.getExceptionCode(), e.getDetails());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResDto> handleAllUnhandledException(Exception ex, WebRequest request) {
        CustomLogger.logError(objectMapper, logger, request, ex, ex.getMessage());

        return ApiResponseUtil.reject(ExceptionCode.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
                .toList();

        String details = String.join(", ", errors);

        CustomLogger.logError(objectMapper, logger, request, ex, details);

        return ApiResponseUtil.reject(ExceptionCode.INVALID_PARAMETER, details, true);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String details = "요청 형식이 잘못되었습니다. 올바른 JSON 형식을 확인해주세요.";
        CustomLogger.logError(objectMapper, logger, request, ex, details);

        return ApiResponseUtil.reject(ExceptionCode.INVALID_PARAMETER, details, true);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String details = "필수 파라미터가 누락되었습니다. " + ex.getParameterName() + " 파라미터를 확인해주세요.";
        CustomLogger.logError(objectMapper, logger, request, ex, details);

        return ApiResponseUtil.reject(ExceptionCode.INVALID_PARAMETER, details, true);
    }
}
