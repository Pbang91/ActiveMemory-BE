package com.example.activememory.global.exception.handler;

import com.example.activememory.global.exception.ExceptionCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(ExceptionCode.INVALID_TOKEN.getHttpStatus().value()); // 401
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write("""
                {
                  "code": "%s",
                  "description": "%s",
                  "details": "%s"
                }
                """.formatted(
                ExceptionCode.INVALID_TOKEN.getCode(),
                ExceptionCode.INVALID_TOKEN.getDescription(),
                authException.getMessage())
        );
    }
}
