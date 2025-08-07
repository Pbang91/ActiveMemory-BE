package com.example.activememory.global.session;

import com.example.activememory.global.security.CustomUserDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomSessionManager {
    public void registerUserSession(HttpServletRequest request, Object user) {
        CustomUserDetail customUserDetail = new CustomUserDetail(UUID.randomUUID(), "d", null);
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetail, null, customUserDetail.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext()
        );
    }
}
