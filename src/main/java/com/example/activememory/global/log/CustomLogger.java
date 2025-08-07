package com.example.activememory.global.log;

import com.example.activememory.global.log.context.LogContext;
import com.example.activememory.global.log.dto.ErrorLog;
import com.example.activememory.global.log.dto.RequestLog;
import com.example.activememory.global.log.dto.ResponseLog;
import com.example.activememory.global.log.wrapper.CustomRequestWrapper;
import com.example.activememory.global.log.wrapper.CustomResponseWrapper;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
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

            if (request.getCachedBodyAsString() == null && request.getQueryString() != null) {
                requestQueryJson = objectMapper.readValue(request.getQueryString(), new TypeReference<>() {});
            } else {
                boolean isJsonBody = request.getContentType() != null
                        && request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE);

                if (!isJsonBody) {
                    requestBodyJson =  new HashMap<>() {
                        {
                            put("size", request.getContentLength());
                        }
                    };
                } else {
                    requestBodyJson = objectMapper.readValue(request.getCachedBodyAsString(), new TypeReference<>() {});
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
            Map<String, Object> responseJson = objectMapper.readValue(responseBody, new TypeReference<>() {
            });

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
