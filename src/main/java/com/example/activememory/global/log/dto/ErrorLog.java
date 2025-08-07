package com.example.activememory.global.log.dto;

public record ErrorLog(
        String traceId,
        long timestamp,
        String uri,
        String message,
        String errorStackTrace,
        String userId
) {
}
