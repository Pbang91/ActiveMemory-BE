package com.example.activememory.global.log.dto;

public record RequestLog(
        String logType,
        String traceId,
        long timestamp,
        String ip,
        String method,
        String uri,
        Object body,
        Object queryParams,
        String userId
) {
}
