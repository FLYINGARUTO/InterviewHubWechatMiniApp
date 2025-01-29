package com.m51.common.util;


import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Data
@Component
public class JwtUtil {

    @Value("${my.jwt.expire}")
    private long expire;
    @Value("${my.jwt.secret}")
    private String secret;
    public String createJwt(Object data){
        //current time
        long currentTime = System.currentTimeMillis();
        //expire time
        long expTime = currentTime + (expire*60*1000);
        //create the Jwt
        JwtBuilder jwtBuilder= Jwts.builder()
                .setId(UUID.randomUUID()+"")
                .setSubject(JSON.toJSONString(data))
                .setIssuer("system")
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expTime))
                .signWith(encodeSecret(secret), SignatureAlgorithm.HS256);
        return jwtBuilder.compact();
    }
    private SecretKey encodeSecret(String s){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
    public Boolean validJwt(String jwt){
        try{
            jwt=jwt.replace("Bearer ","");
            Jwts.parserBuilder()
                    .setSigningKey(encodeSecret(secret))
                    .build()
                    .parseClaimsJwt(jwt);
        }catch (Exception e){
            log.error(e.getMessage());
            return false;

        }
        return true;
    }

    public  <T> T parseJwt(String jwt, Class<T> clazz){
        //jwt=jwt.replace("Bearer ","");
        Claims body= Jwts.parserBuilder()
                .setAllowedClockSkewSeconds(60)
                .setSigningKey(encodeSecret(secret))
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return JSON.parseObject(body.getSubject(),clazz);
    }
}
