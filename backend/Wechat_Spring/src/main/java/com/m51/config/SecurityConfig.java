package com.m51.config;

import com.m51.common.util.JwtUtil;
import com.m51.security.filter.JWTAuthenticationFilter;
import com.m51.security.filter.JsonUsernamePasswordAuthenticationFilter;
import com.m51.security.handler.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //1. 放行部分请求
        http.authorizeHttpRequests(request->{
            request.requestMatchers("/user/register","/user/verification/**","/user/checkCode/**",
            "/user/login","/user/info/**","/user/logout","/user/count/**",
            "/swagger-ui/**","/swagger-resources/**","/v3/**","/file/upload","/base/**")
                    .anonymous()
                    //其余请求都需认证
                    .anyRequest().authenticated();
        });
        //2.登录请求url
//        http.formLogin(form->{
//            form.loginProcessingUrl("/user/login");
//            form.successHandler(new AuthenticationSuccessHandler() {
//                @Override
//                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                    response.getWriter().write("login success");
//                }
//            });
//        });
        //3.前后端分离
        http.sessionManagement(session->{
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        //4.关闭csrf
        http.csrf(csrf->{
            csrf.disable();
        });
        //处理登出请求
        http.logout(logout->{
            logout.logoutUrl("/user/logout")
                    .logoutSuccessHandler( myLogoutSuccessHandler);
        });
        http.exceptionHandling(exception->{
            exception.authenticationEntryPoint(myAuthenticationEntryPoint)
                    .accessDeniedHandler(myAccessDeniedHandler);
        });
        http.addFilterBefore(new JWTAuthenticationFilter(jwtUtil),UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter filter=new JsonUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationConfiguration.getAuthenticationManager());
        filter.setFilterProcessesUrl("/user/login");
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        return filter;
    }
}
