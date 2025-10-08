package com.m51.security.handler;

import com.alibaba.fastjson2.JSON;
import com.m51.common.util.JwtUtil;
import com.m51.common.vo.Result;
import com.m51.file.utils.MinioUtils;
import com.m51.security.entity.SecurityUser;
import com.m51.user.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MinioUtils minioUtils;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        User user1=user.getUser();
        user1.setAvatarUrl(minioUtils.getUrl(user1.getAvatar()));
        String token = jwtUtil.createJwt(user1);
        Result<String> result=Result.Success(token);
        System.out.println(result);
        response.setContentType("application/json");
        response.getWriter().write(JSON.toJSONString(result));


    }
}
