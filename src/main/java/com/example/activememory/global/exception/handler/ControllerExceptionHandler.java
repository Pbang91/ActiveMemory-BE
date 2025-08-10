package com.example.activememory.global.exception.handler;

import com.example.activememory.global.api.ExceptionResDto;
import com.example.activememory.global.exception.ExceptionCode;
import com.example.activememory.global.log.CustomLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResDto> handleAllUnhandledException(Exception ex, WebRequest request) {
        CustomLogger.logError(objectMapper, logger, request, ex, ex.getMessage());

        ExceptionResDto apiExceptionResDto = new ExceptionResDto(
                "9999",
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage()
        );

        return new ResponseEntity<>(apiExceptionResDto, HttpStatus.INTERNAL_SERVER_ERROR);
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

        ExceptionResDto apiExceptionResDto = new ExceptionResDto(
                ExceptionCode.INVALID_PARAMETER.getCode(),
                ExceptionCode.INVALID_PARAMETER.getDescription(),
                details
        );

        return new ResponseEntity<>(apiExceptionResDto, ExceptionCode.INVALID_PARAMETER.getHttpStatus());
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

        ExceptionResDto apiExceptionResDto = new ExceptionResDto(
                ExceptionCode.INVALID_PARAMETER.getCode(),
                ExceptionCode.INVALID_PARAMETER.getDescription(),
                details + ex.getMessage()
        );

        return new ResponseEntity<>(apiExceptionResDto, ExceptionCode.INVALID_PARAMETER.getHttpStatus());
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

        ExceptionResDto apiExceptionResDto = new ExceptionResDto(
                ExceptionCode.INVALID_PARAMETER.getCode(),
                ExceptionCode.INVALID_PARAMETER.getDescription(),
                details
        );

        return new ResponseEntity<>(apiExceptionResDto, ExceptionCode.INVALID_PARAMETER.getHttpStatus());
    }
}
