package com.m51.security.filter;

import com.m51.common.util.JwtUtil;
import com.m51.security.entity.SecurityUser;
import com.m51.user.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Autowired
    public JWTAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri=request.getRequestURI();
        //放行部分uri
        if(!uri.endsWith("user/login")
                &&uri.indexOf("user/info")<0
                &&!uri.endsWith("user/register")
                &&!uri.endsWith("user/logout")
                &&!uri.endsWith("file/upload")
                &&uri.indexOf("user/verification")<0
                &&uri.indexOf("user/checkCode")<0
                &&uri.indexOf("user/count")<0
                &&uri.indexOf("swagger-ui")<0
                &&uri.indexOf("/v3")<0
                &&uri.indexOf("/favicon.ico")<0

        ){
            log.debug("------> jwt authentication started : "+uri);
            String token=request.getHeader("Authorization");
            //in case that token might be null
            if(StringUtils.hasLength(token)){
                token=token.replace("Bearer ","");
                try{
                    User user=jwtUtil.parseJwt(token, User.class);
                    System.out.println(user);
                    //jwt valid if user not null
                    if(user!=null){
                        SecurityUser securityUser=new SecurityUser(user);
                        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                                securityUser,null,securityUser.getAuthorities()
                        ));
                        log.debug("------> jwt authentication succeed : "+uri);
                    }
                }catch (Exception e){
                    log.error("------> jwt authentication failed : "+uri);
                    e.printStackTrace();
                }


            }

        }
        filterChain.doFilter(request,response);
    }
}
