package com.m51.security.handler;

import com.alibaba.fastjson2.JSON;
import com.m51.common.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("🔴 Access Denied Triggered!");
        System.out.println("User: " + (authentication != null ? authentication.getName() : "Anonymous"));
        System.out.println("Roles: " + (authentication != null ? authentication.getAuthorities() : "No roles"));
        System.out.println("Access Denied Reason: " + accessDeniedException.getMessage());
        Result<Object> result=Result.fail(20004,"Access denied due to low level");
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
