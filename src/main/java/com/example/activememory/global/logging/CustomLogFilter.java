package com.example.activememory.global.logging;

import com.example.activememory.global.logging.context.LogContext;
import com.example.activememory.global.logging.wrapper.CustomRequestWrapper;
import com.example.activememory.global.logging.wrapper.CustomResponseWrapper;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class CustomLogFilter extends OncePerRequestFilter {
    private final Logger infoLogger = LoggerFactory.getLogger("INFO_LOGGER");
    private final Logger warnLogger = LoggerFactory.getLogger("WARN_LOGGER");
    private final ObjectMapper objectMapper;

    public CustomLogFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), false);
    }

    private boolean isSwaggerRequest(String uri) {
        return uri.contains("/swagger") || uri.contains("/api-docs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (isSwaggerRequest(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        // CORS용은 넘어가자
        if ("OPTIONS".equals(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        CustomRequestWrapper wrappedRequest = new CustomRequestWrapper(request);
        CustomResponseWrapper wrappedResponse = new CustomResponseWrapper(response);

        String traceId = UUID.randomUUID().toString().replace("-", "").substring(0, 16);

        LogContext.setTraceId(traceId);
        LogContext.setUserId("ANONYMOUS");

        try {
            CustomLogger.logRequest(objectMapper, infoLogger, warnLogger, wrappedRequest);
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            CustomLogger.logResponse(objectMapper, infoLogger, warnLogger, wrappedResponse);
            LogContext.clear();
            wrappedResponse.flushBuffer();
        }
    }
}
