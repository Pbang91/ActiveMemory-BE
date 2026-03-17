package com.example.activememory.global.logging;

import com.example.activememory.global.logging.context.LogContext;
import com.example.activememory.global.logging.dto.ErrorLog;
import com.example.activememory.global.logging.dto.RequestLog;
import com.example.activememory.global.logging.dto.ResponseLog;
import com.example.activememory.global.logging.wrapper.CustomRequestWrapper;
import com.example.activememory.global.logging.wrapper.CustomResponseWrapper;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class CustomLogger {

    private static String getStackTraceAsString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));

        return stringWriter.toString();
    }

    public static void logRequest(ObjectMapper objectMapper, Logger infoLogger, Logger warnLogger, CustomRequestWrapper request) {
        try {
            Map<String, Object> requestBodyJson = null;
            Map<String, Object> requestQueryJson = null;

            // NOTE: Query String 안전하게 추출
            if (request.getParameterMap() != null && !request.getParameterMap().isEmpty()) {
                requestQueryJson = new HashMap<>();

                for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
                    // NOTE: 배열로 들어오는 파라미터라면 ,로 이어서 저장
                    requestQueryJson.put(entry.getKey(), String.join(",", entry.getValue()));
                }
            }

            String bodyAsString = request.getCachedBodyAsString();

            if (StringUtils.isEmpty(bodyAsString)) {
                boolean isJsonBody = request.getContentType() != null
                        && request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE);

                if (isJsonBody) {
                    try {
                        requestBodyJson = objectMapper.readValue(bodyAsString, new TypeReference<Map<String, Object>>() {
                        });
                    } catch (Exception e) {
                        // 만약에 Content-type이 JSON인데 실제 데이터가 깨져 있을 경우에
                        requestBodyJson = new HashMap<>();
                        requestBodyJson.put("body", bodyAsString);
                    }
                } else {
                    requestBodyJson = new HashMap<>();
                    requestBodyJson.put("size", request.getContentLength());
                }
            }

            RequestLog requestLog = new RequestLog(
                    "REQUEST",
                    LogContext.getTraceId(),
                    System.currentTimeMillis(),
                    request.getRemoteAddr(),
                    request.getMethod(),
                    request.getRequestURI(),
                    requestBodyJson,
                    requestQueryJson,
                    LogContext.getUserId()
            );

            infoLogger.info(objectMapper.writeValueAsString(requestLog));
        } catch (Exception e) {
            warnLogger.warn("request 로그 실패. 사유: ", e);
        }
    }

    public static void logResponse(ObjectMapper objectMapper, Logger infoLogger, Logger warnLogger, CustomResponseWrapper response) {
        try {
            String responseBody = new String(response.getCachedBody(), StandardCharsets.UTF_8);
            Map<String, Object> responseJson = null;

            if (responseBody != null && !responseBody.isBlank()) {
                try {
                    responseJson = objectMapper.readValue(responseBody, new TypeReference<>() {
                    });
                } catch (Exception e) {

                }
            }

            ResponseLog responseLog = new ResponseLog(
                    "RESPONSE",
                    LogContext.getTraceId(),
                    System.currentTimeMillis(),
                    response.getStatus(),
                    responseJson
            );

            infoLogger.info(objectMapper.writeValueAsString(responseLog));
        } catch (Exception e) {
            warnLogger.warn("response 로그 실패. 사유: ", e);
        }
    }

    public static void logError(ObjectMapper objectMapper, Logger logger, WebRequest request, Exception ex, String message) {
        try {
            ObjectMapper customObjectMapper = objectMapper.copy();
            customObjectMapper.configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), false);
            String uri = request.getDescription(false).replace("uri=", "");
            ErrorLog errorLog = new ErrorLog(
                    LogContext.getTraceId(),
                    System.currentTimeMillis(),
                    uri,
                    message,
                    getStackTraceAsString(ex),
                    LogContext.getUserId()
            );

            logger.error(customObjectMapper.writeValueAsString(errorLog));
        } catch (Exception e) {
            logger.error("error 로그 실패. 사유: ", e);
        }
    }
}
