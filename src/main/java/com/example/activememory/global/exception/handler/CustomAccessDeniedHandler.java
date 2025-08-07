package com.example.activememory.global.exception.handler;

import com.example.activememory.global.exception.ExceptionCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(ExceptionCode.USER_FORBIDDEN.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write("""
                {
                    "code": "%s",
                    "description": "%s",
                    "details": "%s"
                }
                """.formatted(
                ExceptionCode.USER_FORBIDDEN.getCode(),
                ExceptionCode.USER_FORBIDDEN.getDescription(),
                accessDeniedException.getMessage()
        ));
    }
}
