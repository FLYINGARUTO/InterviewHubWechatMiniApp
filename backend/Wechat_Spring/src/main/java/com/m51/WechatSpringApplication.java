package com.m51;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.m51.*.mapper")
public class WechatSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatSpringApplication.class, args);
    }

}
