package com.example.activememory.global.logging.dto;

public record ErrorLog(
        String traceId,
        long timestamp,
        String uri,
        String message,
        String errorStackTrace,
        String userId
) {
}
