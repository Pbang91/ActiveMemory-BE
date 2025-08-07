package com.example.activememory.global.log.dto;

public record ResponseLog(
        String logType,
        String traceId,
        long timestamp,
        int status,
        Object body
) {
}
