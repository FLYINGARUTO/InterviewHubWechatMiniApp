package com.m51.security.impl;

import com.m51.security.entity.SecurityUser;
import com.m51.user.entity.User;
import com.m51.user.mapper.UserMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("----->loadUserByUsername: "+username);
        User user=userMapper.getUserByUsername(username);
        if(user==null)  throw new UsernameNotFoundException("username not found");
        SecurityUser securityUser= new SecurityUser(user);
        return securityUser;
    }
}
