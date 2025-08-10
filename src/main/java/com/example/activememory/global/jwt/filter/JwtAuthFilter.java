package com.example.activememory.global.jwt.filter;

import com.example.activememory.global.jwt.service.JwtService;
import com.example.activememory.global.log.context.LogContext;
import com.example.activememory.global.security.CustomUserDetail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    private String extractJwtFromRequestHeader(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = extractJwtFromRequestHeader(request);

        if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication authentication = jwtService.buildAuthenticationFromAccessToken(jwt);

            if (authentication != null) {
                if (authentication instanceof UsernamePasswordAuthenticationToken token) {
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                }

                CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();

                LogContext.setUserId(userDetail.getUsername());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
