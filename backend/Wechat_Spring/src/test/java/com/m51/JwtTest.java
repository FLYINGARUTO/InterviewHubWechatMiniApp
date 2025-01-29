package com.m51;

import com.m51.common.util.JwtUtil;
import com.m51.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class JwtTest {

    @Autowired
    private JwtUtil util;

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Test
    public void Test1(){
        User user=new User();
        user.setUsername("Sean");
        user.setEmail("bd@abd.com");
        String jwt= util.createJwt(user);
        System.out.println(jwt);
    }

    @Test
    public void Test2(){
        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyMjY5NTRlOC0wMTQ0LTQyZDAtOTJjOC0yNmJlMDAzODdkZDUiLCJzdWIiOiJ7XCJlbWFpbFwiOlwiYmRAYWJkLmNvbVwiLFwidXNlcm5hbWVcIjpcIlNlYW5cIn0iLCJpc3MiOiJzeXN0ZW0iLCJpYXQiOjE3Mzc1ODQ4NjYsImV4cCI6MTczNzU4ODQ2Nn0.cKjRtn6wazSntdycm4eY4rqCrFwjHGC1MV3WOul22Uk";
        User user=util.parseJwt(jwt, User.class);
        System.out.println(user);
    }
    @Test
    public void Test3(){
        System.out.println(encoder.encode("123456"));
    }
}
